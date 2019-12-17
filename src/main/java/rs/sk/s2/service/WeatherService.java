package rs.sk.s2.service;

import rs.sk.s2.domain.Weather;

import java.util.List;

public interface WeatherService {

    List<Weather> saveWeatherLocaly();

    Weather getWeather(String url);

    List<Weather> getAllWeathers();

    void updateWeather(Weather weather);

    List<Weather> getAllCities(String cities);

    Weather getWeatherByCityAndCountry(String city, String country);

}
