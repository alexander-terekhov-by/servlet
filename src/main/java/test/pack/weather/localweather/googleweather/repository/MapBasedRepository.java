package test.pack.weather.localweather.googleweather.repository;

import test.pack.weather.core.entity.City;
import test.pack.weather.core.repository.LocalWeatherRepository;
import test.pack.weather.localweather.googleweather.entity.LocalWeather;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Alexander Terekhov
 */
public class MapBasedRepository implements LocalWeatherRepository {

    public Map<City, LocalWeather> db;

    public MapBasedRepository() {
        db = new HashMap<>();
        // some prepopulated data
        db.put(new City("Minsk"), new LocalWeather(5));
    }


    public void save(City city, LocalWeather weather) {
        db.put(city, weather);
    }

    public Optional<LocalWeather> get(City city) {
        return Optional.ofNullable(db.get(city));
    }
}