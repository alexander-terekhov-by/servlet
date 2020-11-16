package test.pack;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import test.pack.common.ExceptionHandler;
import test.pack.weather.core.repository.LocalWeatherRepository;
import test.pack.weather.core.service.MockDataManager;
import test.pack.weather.core.service.MockDataManagerImpl;
import test.pack.weather.core.service.impl.CityValidatorImpl;
import test.pack.weather.googleweather.service.GoogleWeatherService;
import test.pack.weather.localweather.googleweather.repository.FileBasedRepository;
import test.pack.weather.localweather.googleweather.repository.MapBasedRepository;
import test.pack.weather.localweather.googleweather.service.LocalWeatherService;
import test.pack.weather.servlet.WeatherServlet;

import java.io.File;

/**
 * @author Alexander Terekhov
 */
public class Main {

    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());
        final String type = args[0];
        WeatherServlet weatherServlet = prepareServlet(type);
        Tomcat.addServlet(ctx, "weather-servlet", weatherServlet);
        ctx.addServletMappingDecoded("/weather", "weather-servlet");

        tomcat.start();
        tomcat.getServer().await();
    }

    public static WeatherServlet prepareServlet(String type) {
        //can act as simple dependency injector
        final ExceptionHandler exceptionHandler = new ExceptionHandler();
        final CityValidatorImpl cityValidator = new CityValidatorImpl();

        WeatherServlet weatherServlet;
        if ("local-map".equals(type)) {
            final LocalWeatherRepository repository = new MapBasedRepository();
            final LocalWeatherService weatherService = new LocalWeatherService(repository);
            final MockDataManagerImpl mockDataManager = new MockDataManagerImpl(repository);
            weatherServlet = new WeatherServlet(cityValidator, weatherService, mockDataManager, exceptionHandler);
        } else if ("local-db".equals(type)) {
            final LocalWeatherRepository repository = new FileBasedRepository();
            final LocalWeatherService weatherService = new LocalWeatherService(repository);
            final MockDataManagerImpl mockDataManager = new MockDataManagerImpl(repository);
            weatherServlet = new WeatherServlet(cityValidator, weatherService, mockDataManager, exceptionHandler);
        } else {
            weatherServlet = new WeatherServlet(cityValidator, new GoogleWeatherService(), new MockDataManager(), exceptionHandler);
        }
        return weatherServlet;
    }
}