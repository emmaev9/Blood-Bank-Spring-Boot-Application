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
    Doctor findDoctorById(Integer id);

    Doctor findDoctorByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsById(Integer id);

    @Modifying
    @Query("UPDATE Doctor d " +
            "set d.lastName = :lastName, d.firstName = :firstName,d.email = :email, d.CNP = :CNP, d.donationCenter = :donationCenter," +
            "d.username = :username  where d.id = :id")
    void updateDoctor(@Param("id") Integer id, @Param("username") String username, @Param("firstName")String firstName, @Param("lastName")String lastName,
                     @Param("email") String email, @Param("CNP") String CNP, @Param("donationCenter") DonationCenter donationCenter);

}
