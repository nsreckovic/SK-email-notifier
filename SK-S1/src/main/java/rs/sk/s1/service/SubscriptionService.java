package rs.sk.s1.service;

import rs.sk.s1.domain.dto.CityDto;
import rs.sk.s1.domain.dto.SubscriptionDto;
import rs.sk.s1.domain.dto.WeatherDto;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDto addSubscription(String email, CityDto cityDto);

    String removeSubscription(String email, CityDto cityDto);

    List<WeatherDto> getSubscribedWeather(String email);

    List<WeatherDto> getNotSubscribedWeather(String email);

}
