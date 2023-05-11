package com.bloodbank.bloodbankapp.Repository;

import com.bloodbank.bloodbankapp.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppoitmentRepository extends JpaRepository<Appoitment, Integer> {
    @Query("SELECT a FROM Appoitment a WHERE a.donor = :donor")
    List<Appoitment> findUserAppoitments(@Param("donor") Donor donor);
    void deleteById(Integer id);
    @Query("SELECT a FROM Appoitment a WHERE a.doctor = :doctor")
    List<Appoitment> getAppoitmentByDoctor(Doctor doctor);
    List<Appoitment> getAppoitmentsByDoctor(Doctor doctor);
    Appoitment findAppoitmentById(@Param("id") Integer id);
    List<Appoitment> findAllByDate(Date date);
    boolean existsById(Integer id);
    List<Appoitment> findAppoitmentsByDonor_Username(String username);
    List<Appoitment> findAppoitmentsByDoctor_Username(String username);
    List<Appoitment> findAppoitmentsByDonationCenter_Id(Integer id);
}
