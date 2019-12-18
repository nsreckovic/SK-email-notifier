package rs.sk.s2.service;

import org.springframework.stereotype.Service;
import rs.sk.s2.domain.Weather;

import java.net.URI;
import java.util.List;

public interface WeatherService {

    List<Weather> saveWeatherLocaly();

    Weather getWeather(String uri);

    List<Weather> getAllWeathers();

    void updateWeather(Weather weather);

    List<Weather> getAllCities(String cities);

    Weather getWeatherByCityAndCountry(String city, String country);

}
