package test.pack.weather.core.entity;

import java.util.Objects;

/**
 * @author Alexander Terekhov
 */
public class City {
    private final String code;

    public City(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(code, city.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
