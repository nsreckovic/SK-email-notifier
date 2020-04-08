package rs.sk.s1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.sk.s1.domain.User;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {

    //@Query(value = "SELECT * FROM user u WHERE u.email = ?1 AND u.password = ?2", nativeQuery = true)
    // Ovaj Query nam ne treba jer Spring MAGICNO radi!
    Optional<User> findByEmailAndPassword(String email, String password);

}
