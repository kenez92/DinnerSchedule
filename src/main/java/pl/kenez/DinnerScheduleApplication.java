package pl.kenez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DinnerScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DinnerScheduleApplication.class, args);
    }

}
