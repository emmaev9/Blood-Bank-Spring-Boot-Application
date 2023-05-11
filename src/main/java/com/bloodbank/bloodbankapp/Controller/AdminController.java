package com.bloodbank.bloodbankapp.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/admin/")
@AllArgsConstructor
public class AdminController {

    @GetMapping("/adminHome")
    public String donorHomePage(){
        return "adminHome";
    }

}
