package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Entity.User;
import com.bloodbank.bloodbankapp.DTO.AppoitmentDTO;
import com.bloodbank.bloodbankapp.Service.AppoitmentService;
import com.bloodbank.bloodbankapp.Service.DonationCenterService;
import com.bloodbank.bloodbankapp.Service.DonorService;
import com.bloodbank.bloodbankapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class DonorController {

    @Autowired
    UserService userService;

    @Autowired
    DonorService donorService;

    @Autowired
    DonationCenterService donationCenterService;

    @Autowired
    AppoitmentService appoitmentService;


    @GetMapping("/DonorHome")
    public ModelAndView donorHomePage(){
        Donor donor = donorService.findDonorByUsername(getUsername());
        ModelAndView mav = new ModelAndView("DonorHome");
        List<Appoitment> appoitmentList = appoitmentService.findUserAppoitments(donorService.findDonorByUsername(getUsername()));
        List<DonationCenter> donationCenterList = donationCenterService.findAllDonationCentersInCounty(donor.getCounty());
        System.out.println(appoitmentList);
        mav.addObject("locations", donationCenterList);
        mav.addObject("appoitmentList", appoitmentList);
        return mav;
    }

    @GetMapping("/registerDonor")
    public String showRegistrationForm(Model model){
        Donor donor = new Donor();

        model.addAttribute("donor", donor);
        return "registerDonor";
    }

    @PostMapping("/registerDonor")
    public String registerDonor(@ModelAttribute Donor registeredDonor,
                                BindingResult result,
                                Model model){
        User existingUser = userService.findUserByUsername(registeredDonor.getUsername());
        if(existingUser != null && existingUser.getUsername()!=null){
            result.rejectValue("username", "error with username",
                    "There is already an account registered with the same username");
        }

        Donor newDonor = new Donor();
        newDonor.setEmail(registeredDonor.getEmail());
        newDonor.setFirstName(registeredDonor.getFirstName());
        newDonor.setLastName(registeredDonor.getLastName());
        newDonor.setPassword(registeredDonor.getPassword());
        newDonor.setUsername(registeredDonor.getUsername());
        newDonor.setBloodType(registeredDonor.getBloodType());
        newDonor.setCounty(registeredDonor.getCounty());
        System.out.println(newDonor.toString());
        donorService.saveDonor(newDonor);
        System.out.println(newDonor.toString());

        return "login";
    }

    @GetMapping("/EditDonorProfile")
    public String editProfileForm(Model model){
        Donor donor = donorService.findDonorByUsername(getUsername());
        model.addAttribute("donor", donor);
        return "/EditDonorProfile";
    }

    @PostMapping("/EditDonorProfile")
    public String editProfile(@ModelAttribute Donor registeredDonor,
                                BindingResult result,
                                Model model){
        User existingUser = userService.findUserByUsername(registeredDonor.getUsername());
        if(existingUser != null && existingUser.getUsername()!=null){
            result.rejectValue("username", "error with username",
                    "There is already an account registered with the same username");
        }

        Donor newDonor = new Donor();
        newDonor.setEmail(registeredDonor.getEmail());
        newDonor.setFirstName(registeredDonor.getFirstName());
        newDonor.setLastName(registeredDonor.getLastName());
        if(registeredDonor.getPassword()!= null) {
            newDonor.setPassword(registeredDonor.getPassword());
        }
        newDonor.setUsername(registeredDonor.getUsername());
        newDonor.setBloodType(registeredDonor.getBloodType());
        newDonor.setCounty(registeredDonor.getCounty());
        donorService.updateDonor(newDonor);
        return "redirect:/DonorHome";
    }

    @GetMapping("/deleteDonor")
    public String deleteDonor() {
        Donor currentDonor = donorService.findDonorByUsername(getUsername());
        appoitmentService.deleteUserAppoitments(currentDonor);
        donorService.deleteDonorById(currentDonor.getId());
        return "redirect:/login";
    }
    @GetMapping("/createAppoitment")
    public String makeAppoitmentGet(Model model){

        model.addAttribute("locations", donationCenterService.findAllDonationCentersInCounty(
                donorService.findDonorByUsername(getUsername()).getCounty()));
        AppoitmentDTO appoitmentDTO = new AppoitmentDTO();
        model.addAttribute("appoitmentDto", appoitmentDTO);
        return "createAppoitment";
    }

    @PostMapping("/createAppoitment")
    public String makeAppoitment(@ModelAttribute AppoitmentDTO appoitment){
        Appoitment newAppoitment = new Appoitment();

        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = formatter.parse(appoitment.getDate());
            newAppoitment.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newAppoitment.setDonor(donorService.findDonorByUsername(getUsername()));
        newAppoitment.setDonationCenter(donationCenterService.findDonationCenterByName(appoitment.getLocation()));
        newAppoitment.setConfirmed(false);
        System.out.println("Location: " + donationCenterService.findDonationCenterByName(appoitment.getLocation()).getName());
        System.out.println("Date: " + appoitment.getDate());
        appoitmentService.saveAppoitment(newAppoitment);
        return "redirect:/DonorHome";
    }


    public String getUsername(){
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username  = principal.toString();
        }
        return username;
    }
}
