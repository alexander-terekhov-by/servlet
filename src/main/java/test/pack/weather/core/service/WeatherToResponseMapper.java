package test.pack.weather.core.service;

import test.pack.weather.core.entity.Weather;

/**
 * @author Alexander Terekhov
 */
public interface WeatherToResponseMapper {

    default String getWeatherRepresentation(Weather weather) {
        if (weather == null) {
            return "Weather in the city is unknown";
        } else {
            return "Weather is " + weather.toString();
        }
    }
}
