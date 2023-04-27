package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.Request.DonationCenterRequestDTO;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Service.DonationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/location/")
public class DonationCenterController {
    @Autowired
    private DonationCenterService donationCenterService;

    @GetMapping("/getAllDonationCenters")
    public ResponseEntity<?> getLocations(){
        List<DonationCenter> donationCenters = donationCenterService.findAllDonationCenters();
        return ResponseEntity.ok(donationCenters);
    }
    @GetMapping("/getDonationCentersWithLabel")
    public ResponseEntity<?> getLoc(){
        List<DonationCenter> donationCenters = donationCenterService.findAllDonationCenters();
        List<DonationCenterRequestDTO> donationCenterRequestDTOS = new ArrayList<>();
        for(DonationCenter d: donationCenters){
            DonationCenterRequestDTO donationCenterRequestDTO = new DonationCenterRequestDTO();
            donationCenterRequestDTO.setCity(d.getCity());
            donationCenterRequestDTO.setCounty(d.getCounty());
            donationCenterRequestDTO.setId(d.getId());
            donationCenterRequestDTO.setName(d.getName());
            donationCenterRequestDTO.setLabel(d.getName() + ", " + d.getCounty() + ", " + d.getCity());
            donationCenterRequestDTOS.add(donationCenterRequestDTO);
        }
        return ResponseEntity.ok(donationCenterRequestDTOS);
    }
}
