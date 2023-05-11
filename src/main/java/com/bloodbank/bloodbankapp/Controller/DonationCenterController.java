package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Facade.DonationCenterFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/location/")
@AllArgsConstructor
public class DonationCenterController {

    private final DonationCenterFacade donationCenterFacade;

    @GetMapping("/getAllDonationCenters")
    public ResponseEntity<?> getAllLocations(){
        return ResponseEntity.ok(donationCenterFacade.getAllDonationCenters());
    }
    @GetMapping("/getDonationCentersWithLabel")
    public ResponseEntity<?> getLoc(){
        return ResponseEntity.ok(donationCenterFacade.getDonationCentersWithLabel());
    }

    @GetMapping("/donationCenters")
    public ResponseEntity<?> getDonorCenters(@RequestParam String county){
        List<DonationCenter> donationCenters = donationCenterFacade.findAllDonationCentersInCounty(county);
        return ResponseEntity.ok(donationCenters);
    }
}
