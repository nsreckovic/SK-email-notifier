package rs.sk.s1.service;

import rs.sk.s1.domain.dto.CityDto;
import rs.sk.s1.domain.dto.LoginDto;
import rs.sk.s1.domain.dto.SubscriptionDto;

public interface SubscriptionService {

    SubscriptionDto addSubscription(String email, CityDto cityDto);

}
