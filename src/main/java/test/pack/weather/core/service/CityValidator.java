package test.pack.weather.core.service;


import test.pack.weather.core.entity.City;

import java.util.Optional;

public interface CityValidator {
    Optional<String> validateCity(City city);
}
