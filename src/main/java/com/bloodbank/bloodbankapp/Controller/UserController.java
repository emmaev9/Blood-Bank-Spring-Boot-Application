package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Entity.User;
import com.bloodbank.bloodbankapp.Service.DonorService;
import com.bloodbank.bloodbankapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/Home")
    public String home(){
        return "Home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/DoctorHome")
    public String doctorHome(){
        System.out.println("Doctor logged in");
        return "DoctorHome";
    }

}
