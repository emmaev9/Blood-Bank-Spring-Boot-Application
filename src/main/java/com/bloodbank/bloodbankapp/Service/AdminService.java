package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Admin;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Repository.AdminRepository;
import com.bloodbank.bloodbankapp.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    public Admin findAdminByUsername(String username){
        return adminRepository.findUserByUsername(username);
    }

    public void saveAdmin(Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        if(roleRepository.findRole("ROLE_ADMIN")==null){
            Role role = new Role("ROLE_ADMIN");
            roleRepository.save(role);
            admin.setRole(role);
        }
        else{
            admin.setRole(roleRepository.findRole("ROLE_ADMIN"));
        }
        adminRepository.save(admin);
    }

}
