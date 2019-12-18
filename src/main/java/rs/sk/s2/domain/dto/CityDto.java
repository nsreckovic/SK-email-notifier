package rs.sk.s2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    private int id;

    private String name;

    private int visibility;

    private CountryDto sys;

    private TemperatureDto main;

}
