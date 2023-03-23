package com.bloodbank.bloodbankapp.Repository;

import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository  extends JpaRepository<Donor, Integer> {
    @Query("SELECT u FROM Donor u WHERE u.username = :username")
    Donor findDonorByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE Donor d " +
            "set d.email = :email, d.county = :county, d.lastName = :lastname, d.firstName = :firstname" +
            "  where d.id = :donorId")
    void updateDonor(@Param("donorId") Integer donorId,@Param("firstname")String firstName, @Param("lastname")String lastname,
                      @Param("email") String email, @Param("county") String county);

    @Query("SELECT u FROM Donor u WHERE u.id = :id")
    Donor findDonorById(@Param("id") Integer id);

}
