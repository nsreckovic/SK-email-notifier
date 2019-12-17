package rs.sk.s2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    private String name;

    private int id;

    private String country;

    private TemperatureDto main;

    private CountryDto sys;

}
