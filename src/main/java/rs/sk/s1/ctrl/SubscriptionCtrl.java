package rs.sk.s1.ctrl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import rs.sk.s1.dao.SubscriptionDao;
import rs.sk.s1.domain.dto.*;
import rs.sk.s1.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping(value = "/subscription")
@RequiredArgsConstructor
public class SubscriptionCtrl {

    private final SubscriptionService subscriptionService;

    private final SubscriptionDao subscriptionDao;

    private static final Logger logger = LoggerFactory.getLogger("SchedulerService");

    @CrossOrigin
    @PostMapping("/save")
    @ResponseBody
    public SubscriptionDto save(@RequestBody SubscriptionDto subscriptionDto) {
        logger.info(subscriptionDto.toString());
        CityDto cityDto = new CityDto();
        cityDto.setId(subscriptionDto.getCity_id());
        cityDto.setName(subscriptionDto.getCity_name());
        return subscriptionService.addSubscription(subscriptionDto.getEmail(), cityDto);
    }

    @CrossOrigin
    @PostMapping("/saveSubscriptions")
    @ResponseBody
    public String saveSubscriptions(@RequestBody SubscriptionsDto subscriptionDto) {
        logger.info(subscriptionDto.toString());
        for (CityDto c : subscriptionDto.getCities()){
            subscriptionService.addSubscription(subscriptionDto.getEmail(), c);
        }
        return "Success";
    }

    @CrossOrigin
    @PostMapping("/removeSubscriptions")
    @ResponseBody
    public String removeSubscriptions(@RequestBody SubscriptionsDto subscriptionDto) {
        logger.info("Remove");
        for (CityDto c : subscriptionDto.getCities()){
            subscriptionService.removeSubscription(subscriptionDto.getEmail(), c);
        }
        return "Success";
    }

    @CrossOrigin
    @PostMapping("/getSubscribedWeather")
    @ResponseBody
    public List<WeatherDto> getSubscribedWeather(@RequestBody String email) {
        return subscriptionService.getSubscribedWeather(email);
    }

    @CrossOrigin
    @PostMapping("/getNotSubscribedWeather")
    @ResponseBody
    public List<WeatherDto> getNotSubscribedWeather(@RequestBody String email) {
        return subscriptionService.getNotSubscribedWeather(email);
    }
}
