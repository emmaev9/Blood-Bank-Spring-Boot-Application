package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Admin;
import com.bloodbank.bloodbankapp.Entity.ERole;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

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
