package rs.sk.s2.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureDto {

    private float temp;

    private float feels_like;

    private float pressure;

    private float humidity;

}
