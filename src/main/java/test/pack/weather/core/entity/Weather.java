package test.pack.weather.core.entity;


public abstract class Weather {

    protected final int degree;

    public Weather(int degree) {
        this.degree = degree;
    }
    public abstract String describe();
}
