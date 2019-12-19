package rs.sk.s1.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.sk.s1.domain.dto.WeatherDto;

import java.util.List;

@FeignClient(name = "service", url = "http://localhost:8081")
public interface CommunicationService {

    @GetMapping("/weather/findByCities/{cities}")
    List<WeatherDto> getWeatherByCity(@PathVariable("cities") String cities);

    /*
     @GetMapping("/findByCities")
     List<WeatherDto> getWeatherByCity(@RequestParam(value = "cities") String cities);
    */

}
