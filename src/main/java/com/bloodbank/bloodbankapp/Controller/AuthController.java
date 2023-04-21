package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.DonorDTO;
import com.bloodbank.bloodbankapp.DTO.Request.LoginRequestDTO;
import com.bloodbank.bloodbankapp.DTO.Response.*;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Mapper.DonorMapper;
import com.bloodbank.bloodbankapp.Security.UserDetailsImpl;
import com.bloodbank.bloodbankapp.Service.DoctorService;
import com.bloodbank.bloodbankapp.Service.DonorService;
import com.bloodbank.bloodbankapp.Service.UserService;
import com.bloodbank.bloodbankapp.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private DonorService donorService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private DonorMapper donorMapper;


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
                    currentDonor.getCounty()));
        }
        else if(roles.contains("ADMIN")){
            return ResponseEntity.ok(new JwtResponseAdmin(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        }
        else {
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

    @PostMapping("/registerDonor")
    public ResponseEntity<?> registerDonor(@RequestBody DonorDTO registeredDonor){
        if(userService.existsByUsername(registeredDonor.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if(userService.existsByEmail(registeredDonor.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        Donor newDonor = donorMapper.donorDTOToModel(registeredDonor);
        newDonor.setPassword(encoder.encode(newDonor.getPassword()));
        donorService.saveDonor(newDonor);
        return ResponseEntity.ok(new MessageResponse("Donor registered successfully"));
    }


}
