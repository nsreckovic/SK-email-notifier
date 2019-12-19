package rs.sk.s1.ctrl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.sk.s1.dao.SubscriptionDao;
import rs.sk.s1.domain.dto.CityDto;
import rs.sk.s1.domain.dto.SubscriptionDto;
import rs.sk.s1.service.SubscriptionService;

@RestController
@RequestMapping(value = "/subscription")
@RequiredArgsConstructor
public class SubscriptionCtrl {

    private final SubscriptionService subscriptionService;

    private final SubscriptionDao subscriptionDao;

    private static final Logger logger = LoggerFactory.getLogger("SchedulerService");

    @PostMapping("/save")
    public SubscriptionDto save(@RequestBody SubscriptionDto subscriptionDto) {
        logger.info(subscriptionDto.toString());
        CityDto cityDto = new CityDto();
        cityDto.setId(subscriptionDto.getCity_id());
        cityDto.setName(subscriptionDto.getCity_name());
        return subscriptionService.addSubscription(subscriptionDto.getEmail(), cityDto);
    }

}
