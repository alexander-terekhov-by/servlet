package test.pack.weather.localweather.googleweather.service;


import test.pack.weather.core.entity.City;
import test.pack.weather.core.entity.Weather;
import test.pack.weather.core.repository.LocalWeatherRepository;
import test.pack.weather.core.service.WeatherService;
import test.pack.weather.localweather.googleweather.entity.LocalWeather;

public class LocalWeatherService implements WeatherService {

    private final LocalWeatherRepository repository;

    public LocalWeatherService(LocalWeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Weather getWeather(City city) {
        //returns some default value when weather does not exist
        //or can be changed to Optional<Weather> to handle situations
        //depending on real use case of the app
        return repository.get(city).orElse(new LocalWeather(0));
    }
}