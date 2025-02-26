package rs.sk.s2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import rs.sk.s2.dao.WeatherDao;
import rs.sk.s2.domain.Weather;
import rs.sk.s2.domain.dto.CityDto;
import rs.sk.s2.service.WeatherService;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private static final String URL_BY_CITY_AND_COUNTRY = "https://api.openweathermap.org/data/2.5/weather?q={city},{country}&units=metric&APPID={key}";
    private static final String KEY = "4d5a685d33ab021191e1a799ea1c6833";

    private final WeatherDao weatherDao;
    private final RestTemplate restTemplate;

    @Override
    public Weather getWeather(String uri) {
        ResponseEntity<CityDto> res = restTemplate.getForEntity(uri, CityDto.class);
        Weather weather = new Weather();
        weather.setId(res.getBody().getId());
        weather.setCountry(res.getBody().getSys().getCountry());
        weather.setCity(res.getBody().getName());
        weather.setTemp(res.getBody().getMain().getTemp());
        weather.setFeels_like(res.getBody().getMain().getFeels_like());
        weather.setPressure(res.getBody().getMain().getPressure());
        weather.setHumidity(res.getBody().getMain().getHumidity());
        weather.setVisibility(res.getBody().getVisibility());
        return weather;
    }

    @Override
    public List<Weather> getAllWeathers() {
        return weatherDao.findAll();
    }

    @Override
    public void updateWeather(Weather weather) {
        weather = getWeatherByCityAndCountry(weather.getCity(), weather.getCountry());
        weatherDao.save(weather);
    }

    @Override
    public List<Weather> getAllCities(String cities) {
        String[] citiesParsed = cities.split(",");
        return weatherDao.findCities(citiesParsed);
    }

    @Override
    public Weather getWeatherByCityAndCountry(String city, String country) {
        URI uri = new UriTemplate(URL_BY_CITY_AND_COUNTRY).expand(city,country,KEY);
        if (uri.isAbsolute()) {
            Weather weather = getWeather(uri.toString());
            weatherDao.save(weather);
            return weather;
        } else {
            return null;
        }

    }

}
