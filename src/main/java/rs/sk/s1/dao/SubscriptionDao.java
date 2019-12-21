package rs.sk.s1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rs.sk.s1.domain.Subscription;
import rs.sk.s1.domain.User;

import javax.transaction.Transactional;
import java.util.List;

public interface SubscriptionDao extends JpaRepository<Subscription, Integer> {

    @Query(value = "SELECT * FROM subscription sub WHERE sub.user = ?1", nativeQuery = true)
    List<Subscription> findSubscriptionsByEmail(String user);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM subscription sub WHERE sub.user = ?1 AND sub.city = ?2", nativeQuery = true)
    void removeSubscription(String user, String city);

    @Query(value = "SELECT * FROM subscription sub WHERE sub.username = ?1 AND sub.city = ?2", nativeQuery = true)
    List<Subscription> getSubscriptionsByUsernameAndCity(String username, String city);

}
