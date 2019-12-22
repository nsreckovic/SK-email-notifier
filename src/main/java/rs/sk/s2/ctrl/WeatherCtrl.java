package rs.sk.s2.ctrl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import rs.sk.s2.domain.Weather;
import rs.sk.s2.service.impl.WeatherServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/weather")
public class WeatherCtrl {

    private final WeatherServiceImpl weatherService;

    private static final Logger logger = LoggerFactory.getLogger("Weather Controller");

    @GetMapping("/{country}/{city}")
    public Weather getWeatherByCityAndCountry(@PathVariable("country") String country, @PathVariable("city") String city){
        return weatherService.getWeatherByCityAndCountry(city, country);
    }

    @GetMapping("/updateAll")
    @Scheduled(fixedRate = 21600000) // Every 6h
    public List<Weather> updateWeather(){
        logger.warn("Updating database...");
        List<Weather> weatherList = weatherService.getAllWeathers();
        for (Weather w : weatherList){
            weatherService.updateWeather(w);
            logger.info("Updated info for \"" + w.getCity() + "\"");
        }
        return weatherService.getAllWeathers();
    }

    @GetMapping("/findByCities/{cities}")
    public List<Weather> getWeatherByCity(@PathVariable("cities") String cities){
        List<Weather> weatherList = weatherService.getAllCities(cities);
        return weatherList;
    }

    @CrossOrigin
    @GetMapping("/findAll")
    @ResponseBody
    public List<Weather> getAllWeathers(){
        return weatherService.getAllWeathers();
    }

}
