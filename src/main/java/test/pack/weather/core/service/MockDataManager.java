package test.pack.weather.core.service;

import test.pack.weather.core.entity.City;
import test.pack.weather.core.entity.Weather;

/**
 * @author Alexander Terekhov
 */
public class MockDataManager {
    public void store (String city, int degree) {
        throw new UnsupportedOperationException("Mock data storage is not supported");
    }
}