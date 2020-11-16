package test.pack.weather.googleweather.service;


import test.pack.weather.core.entity.City;
import test.pack.weather.core.entity.Weather;
import test.pack.weather.core.service.WeatherService;
import test.pack.weather.googleweather.entity.GoogleWeather;

import java.util.Random;

public class GoogleWeatherService implements WeatherService {

    private static final Random random = new Random();

    @Override
    public Weather getWeather(City city) {
        //go to google or call some google aware service
        return new GoogleWeather(random.nextInt(70));
    }
}
