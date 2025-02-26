package rs.sk.s1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.sk.s1.dao.SubscriptionDao;
import rs.sk.s1.dao.UserDao;
import rs.sk.s1.domain.Subscription;
import rs.sk.s1.domain.User;
import rs.sk.s1.domain.dto.MailDto;
import rs.sk.s1.domain.dto.WeatherDto;

import java.util.List;

@Component
@EnableBinding(OutputChannel.class)
@RequiredArgsConstructor
public class Scheduler {

    private final OutputChannel outputChannel;
    private final SubscriptionDao subscriptionDao;
    private final CommunicationService communicationService;
    private final UserDao userDao;

    private String findCities(List<Subscription> subscriptionsList) {
        StringBuilder sb = new StringBuilder();
        for(Subscription s : subscriptionsList) {
            sb.append(s.getCity_name());
            sb.append(",");
        }
        if(sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Scheduled(fixedDelay = 21600000) // Every 6h
    public void publish() {

        List<User> userList = userDao.findAll();

        for(User u : userList) {
            List<Subscription> subscriptionList = subscriptionDao.findSubscriptionsByEmail(u.getEmail());
            if (!subscriptionList.isEmpty()) {
                List<WeatherDto> weatherList = communicationService.getWeatherByCity(findCities(subscriptionList));

                StringBuilder body = new StringBuilder();
                body.append("Dear " + u.getName() + " " + u.getSurname() + ",\n\n");
                body.append("Todays weather for your subscriptions is:\n\n");

                for (WeatherDto w : weatherList) {
                    body.append(w.getCity() + ", " + w.getCountry() + ":\n\n");
                    body.append("\tTemperature: " + w.getTemp() + "°C\n");
                    body.append("\tFeels like: " + w.getFeels_like() + "°C\n");
                    body.append("\tHumidity: " + w.getHumidity() + "%\n");
                    body.append("\tPressure: " + w.getPressure() + "mb\n");
                    body.append("\tVisibility: " + (w.getVisibility() / 1000) + "km\n\n");
                }

                body.append("Your NP weather team!");

                MailDto mailDto = new MailDto(u.getEmail(), "Todays weather", body.toString());
                outputChannel.output().send(MessageBuilder.withPayload(mailDto).build());
            }
        }

    }


}
