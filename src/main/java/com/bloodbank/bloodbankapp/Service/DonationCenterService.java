package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Repository.DonationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationCenterService {
    @Autowired
    private DonationCenterRepository donationCenterRepository;

    public void saveDonationCenter(DonationCenter donationCenter){
        donationCenterRepository.save(donationCenter);
    }

    public List<DonationCenter> findAllDonationCenters(){
        List<DonationCenter> centers = donationCenterRepository.findAll();
        return centers;
    }

    public DonationCenter findDonationCenterByName(String name){
        return donationCenterRepository.findDonationCenterByName(name);
    }

    public List<DonationCenter> findAllDonationCentersInCounty(String county){
        return donationCenterRepository.findAllDonationCentersInCounty(county);
    }

    public DonationCenter findDonationCenterById(Integer id){
        return donationCenterRepository.findDonationCenterById(id);
    }

}
