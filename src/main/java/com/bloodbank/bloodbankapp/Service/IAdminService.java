package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Admin;

public interface IAdminService {
    void saveAdmin(Admin admin);
    boolean existsAdmin(Admin admin);
}
