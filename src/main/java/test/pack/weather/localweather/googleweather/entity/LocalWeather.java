package test.pack.weather.localweather.googleweather.entity;


import test.pack.weather.core.entity.Weather;

public class LocalWeather extends Weather {
    public LocalWeather(int degree) {
        super(degree);
    }

    public int getDegree() {
        return degree;
    }

    @Override
    public String describe() {
        return String.format("Local weather is %s celsius", degree);
    }
}