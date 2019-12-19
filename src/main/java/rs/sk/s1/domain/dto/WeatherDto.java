package rs.sk.s1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

    @Id
    private int id;

    private String country;

    private String city;

    private int temp;

    private int feels_like;

    private int pressure;

    private int humidity;

    private int visibility;

}
