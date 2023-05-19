package com.example.springedu2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springedu2","springjpa","springrest"})
@EnableJpaRepositories(basePackages = {"springjpa.exam.repository"})
@EntityScan(basePackages = {"springjpa.exam.entity","springrest.exam.domain"})
public class Springedu2Application {

    public static void main(String[] args) {
        SpringApplication.run(Springedu2Application.class, args);
    }

}
