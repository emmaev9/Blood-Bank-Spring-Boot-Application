package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;

import java.util.List;

public interface IDonationCenterService {
    List<DonationCenter> findAllDonationCenters();
    List<DonationCenter> findAllDonationCentersInCounty(String county);
    DonationCenter findDonationCenterById(Integer id);
}
