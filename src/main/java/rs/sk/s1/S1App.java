package rs.sk.s1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class S1App {

    public static void main(String[] args) {
        SpringApplication.run(S1App.class, args);
    }

}
