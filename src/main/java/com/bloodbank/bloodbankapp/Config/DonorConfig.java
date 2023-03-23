/*package com.bloodbank.bloodbankapp.Config;

import com.bloodbank.bloodbankapp.Model.Donor;
import com.bloodbank.bloodbankapp.Repository.DonorRepository;
import com.bloodbank.bloodbankapp.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DonorConfig {
    @Bean
    CommandLineRunner commandLineRunner(DonorRepository repo){
        return args -> {
            Donor maria = new Donor(
                    "ana",
                    "maria@yahoo.com",
                    "password",
                    "A+",
                    "Cluj"
                    );
            Donor flavia = new Donor(
                    "flavia",
                    "flavia@yahoo.com",
                    "password",
                    "A+",
                    "Cluj"
            );
            repo.saveAll(List.of(maria, flavia));
        };
    }
    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
}*/