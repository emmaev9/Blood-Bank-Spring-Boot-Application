package com.bloodbank.bloodbankapp.Facade;

import com.bloodbank.bloodbankapp.Entity.User;
import com.bloodbank.bloodbankapp.Service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFacade {
    private final IUserService userService;

   public boolean existsUserByEmail(String email){
       return userService.existsByEmail(email);
   }
   public boolean existsUserByUsername(String username){
       return userService.existsByUsername(username);
   }
   public User findUserByUsername(String username){
       return  userService.findUserByUsername(username);
   }
   public User findUserByEmail(String email){
       return userService.findUserByEmail(email);
   }
   public User findUserById(Integer id){
       return userService.findUserById(id);
   }
}
