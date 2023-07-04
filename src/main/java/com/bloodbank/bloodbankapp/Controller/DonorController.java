package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.Request.DonorDTO;
import com.bloodbank.bloodbankapp.DTO.Response.MessageResponse;
import com.bloodbank.bloodbankapp.Facade.DonorFacade;
import com.bloodbank.bloodbankapp.Facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/donor/")
@AllArgsConstructor
public class DonorController {

    private final UserFacade userFacade;
    private final DonorFacade donorFacade;

    @GetMapping("/donorHome")
    public String donorHomePage(){
        return "donorHome";
    }

    @GetMapping("/editDonorProfile")
    public String editProfileForm(){
        return "editProfile";
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteDonor(@PathVariable(value="id") Integer id){
        donorFacade.deleteDonor(id);
        return ResponseEntity.ok().body(new MessageResponse("Account successfully deleted!"));
    }

    @PostMapping("/registerDonor")
    public ResponseEntity<?> registerDonor(@RequestBody DonorDTO registeredDonor){
        if(userFacade.existsUserByUsername(registeredDonor.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if(userFacade.existsUserByEmail(registeredDonor.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        donorFacade.registerDonor(registeredDonor);
        return ResponseEntity.ok(new MessageResponse("Donor registered successfully"));
    }

    @PostMapping("/editDonorProfile")
    public ResponseEntity<?> editProfileForm(@RequestBody DonorDTO updatedDonor){
        if(userFacade.findUserByUsername(updatedDonor.getUsername()) != null
                && userFacade.findUserById(updatedDonor.getId())!=userFacade.findUserByUsername(updatedDonor.getUsername()) ){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if(userFacade.findUserByEmail(updatedDonor.getEmail()) != null
                && userFacade.findUserById(updatedDonor.getId())!=userFacade.findUserByEmail(updatedDonor.getEmail()) ){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        donorFacade.updateDonor(updatedDonor);
        return ResponseEntity.ok(new MessageResponse("Profile updated!"));
    }

}
