package rs.sk.s2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    private int id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "temp")
    private float temp;

    @Column(name = "feels_like")
    private float feels_like;

    @Column(name = "pressure")
    private float pressure;

    @Column(name = "humidity")
    private float humidity;

    @Column(name = "visibility")
    private float visibility;

}
