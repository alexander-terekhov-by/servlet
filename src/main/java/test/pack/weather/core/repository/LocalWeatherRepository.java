package test.pack.weather.core.repository;

import test.pack.weather.core.entity.City;
import test.pack.weather.localweather.googleweather.entity.LocalWeather;

import java.util.Optional;

/**
 * @author Alexander Terekhov
 */
public interface LocalWeatherRepository {

    //delete method can be added if needed


    void save(City city, LocalWeather weather);

    Optional<LocalWeather> get(City city);
}
