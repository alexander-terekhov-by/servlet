package test.pack.weather.core.service.impl;

import test.pack.weather.core.entity.City;
import test.pack.weather.core.service.CityValidator;

import java.util.Optional;
import java.util.Set;

/**
 * @author Alexander Terekhov
 */
public class CityValidatorImpl implements CityValidator {

    private static final Set<String> ALLOWED_CITIES = Set.of("Minsk", "Tallinn");

    @Override
    public Optional<String> validateCity(City city) {
        if (ALLOWED_CITIES.contains(city.getCode()) ){
            return Optional.empty();
        }
        return Optional.of("Weather can be shown only for cities: " + ALLOWED_CITIES);
    }
}