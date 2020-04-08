package rs.sk.s1.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rs.sk.s1.dao.SubscriptionDao;
import rs.sk.s1.domain.Subscription;
import rs.sk.s1.domain.dto.CityDto;
import rs.sk.s1.domain.dto.SubscriptionDto;
import rs.sk.s1.domain.dto.WeatherDto;
import rs.sk.s1.service.CommunicationService;
import rs.sk.s1.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger("Subscription Service");

    private final SubscriptionDao subscriptionDao;
    private final CommunicationService communicationService;

    @Override
    public SubscriptionDto addSubscription(String email, CityDto cityDto) {
        Subscription subscription = new Subscription();
        subscription.setEmail(email);
        subscription.setCity_id(cityDto.getId());
        subscription.setCity_name(cityDto.getName());
        logger.info("Added subscription: " + subscription.toString());
        subscriptionDao.save(subscription);
        return new SubscriptionDto(subscription.getEmail(), subscription.getCity_id(), subscription.getCity_name());
    }

    @Override
    public String removeSubscription(String email, CityDto cityDto) {
        Subscription subscription = new Subscription();
        subscription.setEmail(email);
        subscription.setCity_id(cityDto.getId());
        subscription.setCity_name(cityDto.getName());
        logger.info("Removed subscription: " + subscription.toString());
        subscriptionDao.removeSubscription(subscription.getEmail(), subscription.getCity_name());
        return "Success!";
    }

    private List<WeatherDto> filterWeather(List<WeatherDto> weatherList, List<Subscription> subscriptionList, boolean sub){
        List<WeatherDto> filterList = new ArrayList<>();
        if (sub){
            for (Subscription s : subscriptionList){
                for (WeatherDto w : weatherList){
                    if (s.getCity_name().equals(w.getCity())) {
                        filterList.add(w);
                        break;
                    }
                }
            }
        } else {
            for (WeatherDto w : weatherList){
                boolean add = true;
                for (Subscription s : subscriptionList){
                    if (s.getCity_name().equals(w.getCity())){
                        add = false;
                        break;
                    }
                }
                if (add) filterList.add(w);
            }
        }
        return filterList;
    }

    @Override
    public List<WeatherDto> getSubscribedWeather(String email) {
        List<WeatherDto> weatherList = communicationService.getAllWeather();
        List<Subscription> subscriptionList = subscriptionDao.findSubscriptionsByEmail(email);
        List<WeatherDto> finalList  = filterWeather(weatherList, subscriptionList, true);
        return finalList;
    }

    @Override
    public List<WeatherDto> getNotSubscribedWeather(String email) {
        List<WeatherDto> weatherList = communicationService.getAllWeather();
        List<Subscription> subscriptionList = subscriptionDao.findSubscriptionsByEmail(email);
        List<WeatherDto> finalList  = filterWeather(weatherList, subscriptionList, false);
        return finalList;
    }

}
