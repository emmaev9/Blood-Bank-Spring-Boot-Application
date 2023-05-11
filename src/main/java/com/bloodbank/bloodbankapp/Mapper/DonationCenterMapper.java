package com.bloodbank.bloodbankapp.Mapper;


import com.bloodbank.bloodbankapp.DTO.Request.DonationCenterRequestDTO;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import org.springframework.stereotype.Component;

@Component
public class DonationCenterMapper {
    public DonationCenterRequestDTO EntityToDtoRequest(DonationCenter donationCenter){
        DonationCenterRequestDTO donationCenterRequestDTO = new DonationCenterRequestDTO();
        donationCenterRequestDTO.setCity(donationCenter.getCity());
        donationCenterRequestDTO.setCounty(donationCenter.getCounty());
        donationCenterRequestDTO.setId(donationCenter.getId());
        donationCenterRequestDTO.setName(donationCenter.getName());
        donationCenterRequestDTO.setLabel(donationCenter.getName() + ", " + donationCenter.getCounty() + ", " + donationCenter.getCity());
        return donationCenterRequestDTO;
    }
}

