package com.bloodbank.bloodbankapp.Repository;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationCenterRepository extends JpaRepository<DonationCenter, Integer> {
    @Query("SELECT d FROM DonationCenter d WHERE d.name = :name")
    DonationCenter findDonationCenterByName(@Param("name") String name);

    @Query("SELECT d FROM DonationCenter d WHERE d.county = :county")
    List<DonationCenter> findAllDonationCentersInCounty(@Param("county") String county);
}
