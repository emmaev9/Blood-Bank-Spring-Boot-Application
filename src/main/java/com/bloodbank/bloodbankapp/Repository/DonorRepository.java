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

    Donor findDonorByUsername(@Param("username") String username);

    @Modifying
    @Query("UPDATE Donor d " +
            "set d.email = :email, " +
            "    d.county = :county, " +
            "    d.lastName = :lastname, " +
            "    d.firstName = :firstname," +
            "    d.password = :password," +
            "    d.bloodType = :bloodType," +
            "    d.username = :username," +
            "    d.phoneNumber = :phoneNumber" +
            "  where d.id = :donorId")
    void updateDonor(@Param("donorId") Integer donorId,@Param("firstname")String firstName, @Param("lastname")String lastname,
                      @Param("email") String email, @Param("county") String county, @Param("password") String password,
                     @Param("username") String username, @Param("bloodType") String bloodType, @Param("phoneNumber") String phoneNumber);


    Donor findDonorById(@Param("id") Integer id);
    void delete(Donor donor);
}
