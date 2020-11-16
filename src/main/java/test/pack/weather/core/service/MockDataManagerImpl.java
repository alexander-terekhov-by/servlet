package test.pack.weather.core.service;

import test.pack.weather.core.entity.City;
import test.pack.weather.core.repository.LocalWeatherRepository;
import test.pack.weather.localweather.googleweather.entity.LocalWeather;

/**
 * @author Alexander Terekhov
 */
public class MockDataManagerImpl extends MockDataManager {

    private final LocalWeatherRepository repository;

    public MockDataManagerImpl(LocalWeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void store(String city, int weather) {
        repository.save(new City(city), new LocalWeather(weather));
    }
}