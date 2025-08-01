package org.example.scheduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScheduleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApiApplication.class, args);
    }

}
