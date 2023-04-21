package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.DonorDTO;
import com.bloodbank.bloodbankapp.DTO.Response.MessageResponse;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Mapper.DonorMapper;
import com.bloodbank.bloodbankapp.Service.AppoitmentService;
import com.bloodbank.bloodbankapp.Service.DonationCenterService;
import com.bloodbank.bloodbankapp.Service.DonorService;
import com.bloodbank.bloodbankapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/donor/")
public class DonorController {

    @Autowired
    UserService userService;

    @Autowired
    DonorService donorService;

    @Autowired
    DonationCenterService donationCenterService;

    @Autowired
    AppoitmentService appoitmentService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    DonorMapper donorMapper;

    @GetMapping("/donorHome")
    public String donorHomePage(){
        return "donorHome";
    }

    @GetMapping("/donationCenters")
    public ResponseEntity<?> getDonorCenters(@RequestParam String county){
        List<DonationCenter> donationCenters = donationCenterService.findAllDonationCentersInCounty(county);
        return ResponseEntity.ok(donationCenters);
    }

    @GetMapping("/editDonorProfile")
    public String editProfileForm(){
        return "editProfile";
    }

    @PostMapping("/editDonorProfile")
    public ResponseEntity<?> editProfileForm(@RequestBody DonorDTO updatedDonor){
        Integer id = updatedDonor.getId();
        if(userService.findUserByUsername(updatedDonor.getUsername()) != null
                && userService.findUserById(id)!=userService.findUserByUsername(updatedDonor.getUsername()) ){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if(userService.findUserByEmail(updatedDonor.getEmail()) != null
                && userService.findUserById(id)!=userService.findUserByEmail(updatedDonor.getEmail()) ){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        Donor donor = donorMapper.donorDTOToModel(updatedDonor);
        donor.setPassword(encoder.encode(updatedDonor.getPassword()));
        donorService.updateDonor(donor);
        return ResponseEntity.ok(new MessageResponse("Profile updated!"));
    }

    @DeleteMapping("/deleteDonor{username}")
    public ResponseEntity<?> deleteDonor(@PathVariable String username) {
        appoitmentService.deleteUserAppoitments(donorService.findDonorByUsername(username));
        donorService.deleteDonorById(donorService.findDonorByUsername(username).getId());
        return ResponseEntity.ok(new MessageResponse("Account deleted!"));
    }
}
