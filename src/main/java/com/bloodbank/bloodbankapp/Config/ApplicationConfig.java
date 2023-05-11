package com.bloodbank.bloodbankapp.Config;

import com.bloodbank.bloodbankapp.Entity.Admin;
import com.bloodbank.bloodbankapp.Entity.ERole;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Service.Implementation.AdminService;
import com.bloodbank.bloodbankapp.Service.Implementation.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    CommandLineRunner commandLineRunner2(AdminService adminService, RoleService roleService){
        return args -> {
            Role role1 = new Role(ERole.ADMIN);
            Role role2 = new Role(ERole.DOCTOR);
            Role role3 = new Role(ERole.DONOR);

            if(!roleService.existsRole(ERole.ADMIN)){
                roleService.addRoleToDB(role1);
            }
            if(!roleService.existsRole(ERole.DOCTOR)){
                roleService.addRoleToDB(role2);
            }
            if(!roleService.existsRole(ERole.DONOR)){
                roleService.addRoleToDB(role3);
            }

            Admin admin1 = new Admin(
                    "Nicolae",
                    "Pop",
                    "admin",
                    "admin@yahoo.com",
                    "admin"
            );
            if(!adminService.existsAdmin(admin1)){
                adminService.saveAdmin(admin1);
            }
        };
    }
}
