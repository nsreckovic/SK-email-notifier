package rs.sk.s2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.sk.s2.domain.Weather;

import java.util.List;

public interface WeatherDao extends JpaRepository<Weather, Integer> {

    @Query(value = "SELECT * FROM weather w WHERE w.city IN :cities", nativeQuery = true)
    List<Weather> findCities(@Param("cities") String[] weatherCollection);

}
