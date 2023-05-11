package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.Request.LoginRequestDTO;
import com.bloodbank.bloodbankapp.DTO.Response.*;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Security.UserDetailsImpl;
import com.bloodbank.bloodbankapp.Service.IDoctorService;
import com.bloodbank.bloodbankapp.Service.IDonorService;
import com.bloodbank.bloodbankapp.Utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IDonorService donorService;
    private final IDoctorService doctorService;
    private final JwtUtils jwtUtils;

    @GetMapping("/Home")
    public String home(){
        return "Home";
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Donor currentDonor = donorService.findDonorByUsername(userDetails.getUsername());
        Doctor currentDoctor = doctorService.findDoctorByUsername(userDetails.getUsername());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        if(roles.contains("DONOR")) {
            return ResponseEntity.ok(new JwtResponseDonor(jwt,
                    userDetails.getPassword(),
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles,
                    currentDonor.getFirstName(),
                    currentDonor.getLastName(),
                    currentDonor.getBloodType(),
                    currentDonor.getCounty(),
                    currentDonor.getPhoneNumber()));
        }
        else if(roles.contains("ADMIN")){
            return ResponseEntity.ok(new JwtResponseAdmin(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        }
        else { //DOCTOR
            return ResponseEntity.ok(new JwtResponseDoctor(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles,
                    currentDoctor.getFirstName(),
                    currentDoctor.getLastName(),
                    currentDoctor.getDonationCenter().getName(),
                    currentDoctor.getCNP()));
        }
    }
}
