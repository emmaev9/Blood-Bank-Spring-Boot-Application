package com.bloodbank.bloodbankapp.Config;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Repository.DonationCenterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DonationCenterConfig {
    @Bean
    CommandLineRunner commandLineRunner3(DonationCenterRepository repo){
        return args -> {
            DonationCenter donationCenter1 = new DonationCenter(
                    "Regina Maria Donation Center",
                    "Cluj",
                "Cluj-Napoca",
                "Mihai Eminescu",
                "123",
                    true,
                    "8:00 - 16:00",
                    3
            );
            DonationCenter donationCenter2 = new DonationCenter(
                    "Synevo Donation Center",
                    "Cluj",
                    "Dej",
                    "1 Mai",
                    "59",
                    true,
                    "10:00-12:00",
                    7
            );
            DonationCenter donationCenter3 = new DonationCenter(
                    "Cardiomed Donation Center",
                    "Maramures",
                    "Baia Mare",
                    "Popesci",
                    "111A",
                    true,
                    "8:00 - 14:00",
                    5
            );
            DonationCenter donationCenter4 = new DonationCenter(
                    "Cardiocad Donation Center",
                    "Cluj",
                    "Cluj-Napoca",
                    "Popesci",
                    "111A",
                    true,
                    "8:00 - 14:00",
                    5
            );

            DonationCenter donationCenter5 = new DonationCenter(
                    "Alba Donation Center",
                    "Alba",
                    "Alba Iulia",
                    "b-dul Revolutiei",
                    "23",
                    true,
                    "8:00 - 14:00",
                    10
            );

            DonationCenter donationCenter6 = new DonationCenter(
                    "Donation Center X",
                    "Suceava",
                    "Suceava",
                    "B-dul 1 Decembrie ",
                    "11",
                    true,
                    "8:00 - 12:00",
                    6
            );

            List<DonationCenter> centers =(List.of(donationCenter1,donationCenter2,donationCenter3, donationCenter4, donationCenter5, donationCenter6));
            for(DonationCenter c: centers){
                if(!repo.existsByCountyAndName(c.getCounty(),c.getName())){
                    repo.save(c);
                }
            }
        };
    }
}
