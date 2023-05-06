package com.bloodbank.bloodbankapp.Repository;

import com.bloodbank.bloodbankapp.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;

public interface AppoitmentRepository extends JpaRepository<Appoitment, Integer> {
    @Query("SELECT a FROM Appoitment a WHERE a.donor = :donor")
    List<Appoitment> findUserAppoitments(@Param("donor") Donor donor);
    void deleteById(Integer id);
    @Query("SELECT a FROM Appoitment a WHERE a.doctor = :doctor")
    List<Appoitment> getAppoitmentByDoctor(@Param("doctor") Doctor doctor);
    Appoitment findAppoitmentById(@Param("id") Integer id);
    List<Appoitment> findAllByDate(Date date);
    List<Appoitment> findAllByDonationCenter(DonationCenter donationCenter);
}
