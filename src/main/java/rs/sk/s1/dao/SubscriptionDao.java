package rs.sk.s1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.sk.s1.domain.Subscription;
import rs.sk.s1.domain.User;

import java.util.List;

public interface SubscriptionDao extends JpaRepository<Subscription, Integer> {

    @Query(value = "SELECT * FROM subscription sub WHERE sub.username = ?1", nativeQuery = true)
    List<Subscription> getSubscriptionsByUsername(String username);

    @Query(value = "SELECT * FROM subscription sub WHERE sub.username = ?1 AND sub.city = ?2", nativeQuery = true)
    List<Subscription> getSubscriptionsByUsernameAndCity(String username, String city);

}
