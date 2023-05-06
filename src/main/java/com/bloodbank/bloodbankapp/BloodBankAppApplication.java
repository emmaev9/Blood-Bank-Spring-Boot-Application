package com.bloodbank.bloodbankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableWebMvc
@EnableScheduling
public class BloodBankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloodBankAppApplication.class, args);

    }

}
