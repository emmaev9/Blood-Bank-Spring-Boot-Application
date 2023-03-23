package com.bloodbank.bloodbankapp.Repository;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppoitmentRepository extends JpaRepository<Appoitment, Integer> {
    @Query("SELECT a FROM Appoitment a WHERE a.donor = :donor")
    List<Appoitment> findUserAppoitments(@Param("donor") Donor donor);
}
