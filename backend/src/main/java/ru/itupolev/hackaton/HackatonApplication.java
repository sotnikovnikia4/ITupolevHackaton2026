package ru.itupolev.hackaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HackatonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HackatonApplication.class, args);
    }

}
