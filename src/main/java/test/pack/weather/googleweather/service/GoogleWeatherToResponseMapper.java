package test.pack.weather.googleweather.service;


import test.pack.weather.core.entity.Weather;
import test.pack.weather.core.service.WeatherToResponseMapper;
import test.pack.weather.googleweather.entity.GoogleWeather;

public class GoogleWeatherToResponseMapper implements WeatherToResponseMapper {

    @Override
    public String getWeatherRepresentation(Weather weather) {
        return ((GoogleWeather) weather).toString() + "was received from Google";
    }
}
