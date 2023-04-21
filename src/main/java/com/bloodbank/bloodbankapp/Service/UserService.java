package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.User;
import com.bloodbank.bloodbankapp.Repository.RoleRepository;
import com.bloodbank.bloodbankapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserService(){}

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public User findUserById(Integer id){
        return userRepository.findUserById(id);
    }
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
