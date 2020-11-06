package test.pack.weather.core.service;


import test.pack.weather.core.entity.City;
import test.pack.weather.core.entity.Weather;

public interface WeatherService {
   Weather getWeather(City city) ;
}
