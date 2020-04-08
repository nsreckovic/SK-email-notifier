package rs.sk.s3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class S3App {

    public static void main(String[] args) {
        SpringApplication.run(S3App.class, args);
    }

}
