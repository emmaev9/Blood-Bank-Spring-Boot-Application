package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.ERole;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void addRoleToDB(Role role){
        roleRepository.save(role);
    }

    public boolean existsRole(ERole role){
        return roleRepository.existsByName(role);
    }

    public Role findRoleByName(ERole role){
        return roleRepository.findByName(role);
    }
}
