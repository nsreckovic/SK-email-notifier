package rs.sk.s1.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.sk.s1.domain.dto.WeatherDto;

import java.util.List;

@FeignClient(name = "service", url = "http://localhost:8082")
public interface CommunicationService {

    @GetMapping("/weather/findByCities/{cities}")
    List<WeatherDto> getWeatherByCity(@PathVariable("cities") String cities);


    @GetMapping("/weather/findAll")
    List<WeatherDto> getAllWeather();


}
