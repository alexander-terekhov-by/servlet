package test.pack.weather.googleweather.entity;


import test.pack.weather.core.entity.Weather;

public class GoogleWeather extends Weather {
    public GoogleWeather(int degree) {
        super(degree);
    }

    @Override
    public String describe() {
        return String.format("Weather from google %s fahrenheit", degree);
    }
}
