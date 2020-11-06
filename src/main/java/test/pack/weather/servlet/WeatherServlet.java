package test.pack.weather.servlet;

import test.pack.common.ExceptionHandler;
import test.pack.weather.core.entity.City;
import test.pack.weather.core.entity.Weather;
import test.pack.weather.core.service.CityValidator;
import test.pack.weather.core.service.WeatherToResponseMapper;
import test.pack.weather.core.service.WeatherService;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WeatherServlet extends HttpServlet {

    private static String cityQueryParam = "city";
    private CityValidator cityValidator;
    private WeatherService weatherService;
    private WeatherToResponseMapper responseTransformer;
    private ExceptionHandler exceptionHandler;

    public WeatherServlet(CityValidator cityValidator, WeatherService weatherService, WeatherToResponseMapper responseTransformer, ExceptionHandler exceptionHandler) {
        this.cityValidator = cityValidator;
        this.weatherService = weatherService;
        this.responseTransformer = responseTransformer;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
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
            final String weatherRepresentation = responseTransformer.getWeatherRepresentation(cityWeather);
            resp.getWriter().print(weatherRepresentation);
        } catch (Exception e) {
            exceptionHandler.writeError(e, resp);
        }
    }
}
