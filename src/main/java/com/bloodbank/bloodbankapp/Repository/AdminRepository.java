package com.bloodbank.bloodbankapp.Repository;

import com.bloodbank.bloodbankapp.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    boolean existsByUsername(String username);
    Admin findUserByUsername(String username);
}
