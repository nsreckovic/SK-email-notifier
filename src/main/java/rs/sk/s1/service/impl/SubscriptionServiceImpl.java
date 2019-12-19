package rs.sk.s1.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rs.sk.s1.dao.SubscriptionDao;
import rs.sk.s1.domain.Subscription;
import rs.sk.s1.domain.dto.CityDto;
import rs.sk.s1.domain.dto.LoginDto;
import rs.sk.s1.domain.dto.SubscriptionDto;
import rs.sk.s1.service.SubscriptionService;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger("SchedulerService");

    private final SubscriptionDao subscriptionDao;

    @Override
    public SubscriptionDto addSubscription(String email, CityDto cityDto) {
        Subscription subscription = new Subscription();
        subscription.setEmail(email);
        subscription.setCity_id(cityDto.getId());
        subscription.setCity_name(cityDto.getName());
        logger.info(subscription.toString());
        subscriptionDao.save(subscription);

        return new SubscriptionDto(subscription.getEmail(), subscription.getCity_id(), subscription.getCity_name());
    }
}
