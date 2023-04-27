package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.DoctorDTO;
import com.bloodbank.bloodbankapp.DTO.Response.MessageResponse;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Mapper.DoctorMapper;
import com.bloodbank.bloodbankapp.Service.DoctorService;
import com.bloodbank.bloodbankapp.Service.DonationCenterService;
import com.bloodbank.bloodbankapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @GetMapping("/adminHome")
    public String donorHomePage(){
        return "adminHome";
    }
}
