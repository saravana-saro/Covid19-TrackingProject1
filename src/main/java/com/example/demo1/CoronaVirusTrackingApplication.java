package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ComponentScan(basePackages = "com.example.demo.services")
@EnableJpaRepositories(basePackages = "com.example.demo.repository") // Replace 'com.example.demo.repository' with the actual package where repositories are located
@EntityScan("com.example.demo.model") // Replace 'com.example.demo.model' with the actual package where entities are located
public class CoronaVirusTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronaVirusTrackingApplication.class, args);
    }
}
