package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.ERole;
import com.bloodbank.bloodbankapp.Entity.Role;

public interface IRoleService {

    void addRoleToDB(Role role);
    boolean existsRole(ERole role);
    Role findRoleByName(ERole role);
}
