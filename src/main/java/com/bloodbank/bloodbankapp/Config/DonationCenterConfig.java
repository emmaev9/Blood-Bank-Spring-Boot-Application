/*package com.bloodbank.bloodbankapp.Config;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Repository.DonationCenterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DonationCenterConfig {
    @Bean
    CommandLineRunner commandLineRunner1(DonationCenterRepository repo){
        return args -> {
            DonationCenter donationCenter1 = new DonationCenter(
                    "Regina Maria Donation Center",
                    "Cluj",
                "Cluj-Napoca",
                "Mihai Eminescu",
                "123",
                    true,
                    "8:00 - 16:00",
                    50
            );
            DonationCenter donationCenter2 = new DonationCenter(
                    "Synevo Donation Center",
                    "Cluj",
                    "Dej",
                    "1 Mai",
                    "59",
                    true,
                    "10:00-12:00",
                    40
            );
            DonationCenter donationCenter3 = new DonationCenter(
                    "Cardiomed Donation Center",
                    "Maramures",
                    "Baia Mare",
                    "Popesci",
                    "111A",
                    true,
                    "8:00 - 14:00",
                    30
            );

            DonationCenter donationCenter4 = new DonationCenter(
                    "Alba Donation Center",
                    "Alba",
                    "Alba Iulia",
                    "b-dul Revolutiei",
                    "23",
                    true,
                    "8:00 - 14:00",
                    30
            );

            DonationCenter donationCenter5 = new DonationCenter(
                    "Donation Center X",
                    "Suceava",
                    "Suceava",
                    "B-dul 1 Decembrie ",
                    "11",
                    true,
                    "8:00 - 12:00",
                    60
            );

            repo.saveAll(List.of(donationCenter1,donationCenter2,donationCenter3, donationCenter4, donationCenter5));
        };
    }
}*/
