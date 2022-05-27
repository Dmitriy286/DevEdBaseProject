package com.example.devedbaseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.devedbaseproject.repository")
//@EnableTransactionManagement
//@EntityScan(basePackages = "com.example.devedbaseproject.models")
public class DevEdBaseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevEdBaseProjectApplication.class, args);
    }

}
