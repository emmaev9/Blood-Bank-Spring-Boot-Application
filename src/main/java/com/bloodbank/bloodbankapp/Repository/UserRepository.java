package com.bloodbank.bloodbankapp.Repository;

import com.bloodbank.bloodbankapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    User findUserById( Integer id);
    User findUserByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    User findByEmail(String email);

}
