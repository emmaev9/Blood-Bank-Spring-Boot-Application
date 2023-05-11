package com.bloodbank.bloodbankapp.Mapper;

import com.bloodbank.bloodbankapp.DTO.Request.DonorDTO;
import com.bloodbank.bloodbankapp.Entity.Donor;
import org.springframework.stereotype.Component;

@Component
public class DonorMapper {
    public Donor donorDTOToModel(DonorDTO donorDTO){
        Donor donor = new Donor();
        donor.setEmail(donorDTO.getEmail());
        donor.setFirstName(donorDTO.getFirstName());
        donor.setLastName(donorDTO.getLastName());
        donor.setPassword(donorDTO.getPassword());
        donor.setUsername(donorDTO.getUsername());
        donor.setBloodType(donorDTO.getBloodType());
        donor.setCounty(donorDTO.getCounty());
        donor.setPhoneNumber(donorDTO.getPhoneNumber());
        return donor;
    }
}
