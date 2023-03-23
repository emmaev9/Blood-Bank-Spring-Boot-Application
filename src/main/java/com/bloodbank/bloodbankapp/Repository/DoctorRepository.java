package com.bloodbank.bloodbankapp.Repository;

import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    @Query("SELECT u FROM Doctor u WHERE u.firstName = :firstName and u.lastName = :lastName")
    Doctor findDoctorByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT u FROM Doctor u WHERE u.id = :id")
    Doctor findDoctorById(@Param("id") Integer id);

    @Query("SELECT u FROM Doctor u WHERE u.username = :username")
    Doctor findDoctorByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE Doctor d " +
            "set d.lastName = :lastName, d.firstName = :firstName,d.email = :email, d.CNP = :CNP, d.donationCenter = :donationCenter" +
            "  where d.username = :username")
    void updateDoctor(@Param("username") String username,@Param("firstName")String firstName, @Param("lastName")String lastName,
                     @Param("email") String email, @Param("CNP") String CNP, @Param("donationCenter") DonationCenter donationCenter);

}
