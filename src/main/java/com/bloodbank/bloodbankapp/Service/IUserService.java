package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.User;

public interface IUserService {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findUserById(Integer id);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
