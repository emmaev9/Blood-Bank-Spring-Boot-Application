package com.bloodbank.bloodbankapp.Config;

import com.bloodbank.bloodbankapp.Entity.Admin;
import com.bloodbank.bloodbankapp.Service.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminConfig {

    @Bean
    CommandLineRunner commandLineRunner2(AdminService adminService){
        return args -> {
            Admin admin1 = new Admin(
                    "Nicolae",
                    "Pop",
                    "admin",
                    "admin@yahoo.com",
                    "admin"
            );
            if(adminService.findAdminByUsername(admin1.getUsername())==null){
                adminService.saveAdmin(admin1);
            }
        };
    }
    @Bean
    PasswordEncoder getEncoder2() {
        return new BCryptPasswordEncoder();
    }
}
