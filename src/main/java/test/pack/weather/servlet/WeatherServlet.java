package test.pack.weather.servlet;

import test.pack.common.ExceptionHandler;
import test.pack.weather.core.entity.City;
import test.pack.weather.core.entity.Weather;
import test.pack.weather.core.service.CityValidator;
import test.pack.weather.core.service.MockDataManager;
import test.pack.weather.core.service.WeatherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WeatherServlet extends HttpServlet {

    private static final String cityQueryParam = "city";
    private final CityValidator cityValidator;
    private final WeatherService weatherService;
    private final MockDataManager mockDataManager;
    private final ExceptionHandler exceptionHandler;

    public WeatherServlet(CityValidator cityValidator, WeatherService weatherService, MockDataManager mockDataManager, ExceptionHandler exceptionHandler) {
        this.cityValidator = cityValidator;
        this.weatherService = weatherService;
        this.mockDataManager = mockDataManager;
        this.exceptionHandler = exceptionHandler;
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        serviceWithErrorHandling(request, response, (req, resp) -> {
            final String cityCode = req.getParameter(cityQueryParam);
            if (cityCode == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "No city code was specified");
                return;
            }

            final City city = new City(cityCode);
            final Optional<String> errors = cityValidator.validateCity(city);
            if (errors.isPresent()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, errors.get());
                return;
            }

            final Weather cityWeather = weatherService.getWeather(city);
            resp.getWriter().print(cityWeather.describe());
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        serviceWithErrorHandling(request, response, (req, resp) -> {
            String cityAndWeather = new BufferedReader(
                    new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            String city;
            int weather;
            try {
                final String[] citySeparatorWeather = cityAndWeather.split(":");
                city = citySeparatorWeather[0];
                weather = Integer.parseInt(citySeparatorWeather[1]);
                mockDataManager.store(city, weather);
            } catch (Exception e) {
                // some more sophisticated validation can be done, but it's test project
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Body should be in format city:weather");
            }

            resp.getWriter().print("Ok!");
        });
    }

    public void serviceWithErrorHandling(HttpServletRequest req, HttpServletResponse resp, ThrowingBiConsumer<HttpServletRequest, HttpServletResponse> consumer) {
        try {
            consumer.consume(req, resp);
        } catch (Exception e) {
            exceptionHandler.writeError(e, resp);
        }
    }

    private interface ThrowingBiConsumer<T1, T2> {
        void consume(T1 a, T2 b) throws Exception;
    }
}
