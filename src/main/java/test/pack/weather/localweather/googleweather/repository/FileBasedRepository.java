package test.pack.weather.localweather.googleweather.repository;

import test.pack.weather.core.entity.City;
import test.pack.weather.core.repository.LocalWeatherRepository;
import test.pack.weather.localweather.googleweather.entity.LocalWeather;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Alexander Terekhov
 */
public class FileBasedRepository implements LocalWeatherRepository {

    public static final String SEPARATOR = ":";
    public static final String RECORD_SEPARATOR = "\n";
    private final String filePath = "src/main/resources/db";


    public void save(City city, LocalWeather weather) {
        try {
            Files.writeString(Path.of(filePath), convertToRecord(city, weather), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Unable to write into file", e);
        }
    }

    public Optional<LocalWeather> get(City city) {
        // lets pretend to be log based db - latest record wins
        try {
            final Map<City, LocalWeather> db = Files.lines(Path.of(filePath))
                    .collect(Collectors.toMap(this::extractCity, this::extractWeather, (a, b) -> b));
            return Optional.ofNullable(db.get(city));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read file", e);
        }
    }

    public String convertToRecord(City city, LocalWeather weather) {
        return city.getCode() + SEPARATOR + weather.getDegree() + RECORD_SEPARATOR;
    }

    public City extractCity(String record) {
        return new City(record.split(SEPARATOR)[0]);
    }

    public LocalWeather extractWeather(String record) {
        return new LocalWeather(Integer.parseInt(record.split(SEPARATOR)[1]));
    }
}