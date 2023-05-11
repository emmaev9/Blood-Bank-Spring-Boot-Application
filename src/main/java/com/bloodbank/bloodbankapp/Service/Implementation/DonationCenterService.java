package com.bloodbank.bloodbankapp.Service.Implementation;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Repository.DonationCenterRepository;
import com.bloodbank.bloodbankapp.Service.IDonationCenterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DonationCenterService implements IDonationCenterService {

    private final DonationCenterRepository donationCenterRepository;

    public List<DonationCenter> findAllDonationCenters(){
        return donationCenterRepository.findAll();
    }

    public List<DonationCenter> findAllDonationCentersInCounty(String county){
        return donationCenterRepository.findAllDonationCentersInCounty(county);
    }

    public DonationCenter findDonationCenterById(Integer id){
        return donationCenterRepository.findDonationCenterById(id);
    }

}
