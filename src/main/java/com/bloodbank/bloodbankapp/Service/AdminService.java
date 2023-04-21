package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Admin;
import com.bloodbank.bloodbankapp.Entity.ERole;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public Admin findAdminByUsername(String username){
        return adminRepository.findUserByUsername(username);
    }

    public boolean existsAdmin(Admin admin){
        return adminRepository.existsByUsername(admin.getUsername());
    }

    public void saveAdmin(Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        Role adminRole = roleService.findRoleByName(ERole.ADMIN);
        roleSet.add(adminRole);
        admin.setRoles(roleSet);
        adminRepository.save(admin);
    }

}
