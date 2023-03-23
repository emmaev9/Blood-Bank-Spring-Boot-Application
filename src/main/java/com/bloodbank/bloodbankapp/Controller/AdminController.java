package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.User;
import com.bloodbank.bloodbankapp.DTO.DoctorDTO;
import com.bloodbank.bloodbankapp.Service.AdminService;
import com.bloodbank.bloodbankapp.Service.DoctorService;
import com.bloodbank.bloodbankapp.Service.DonationCenterService;
import com.bloodbank.bloodbankapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @Autowired
    private DonationCenterService donationCenterService;

    @GetMapping("/AdminHome")
    public String adminHomePage(Model model){
        System.out.println("Admin logged in");

        List<Doctor> listDoctors = doctorService.findAllDoctors();
        List<String> locations = new ArrayList<>();
        for(Doctor d: listDoctors){
            locations.add(d.getDonationCenter().getName());
        }
        model.addAttribute("doctors", listDoctors );
       // model.addAttribute("locations", locations);

        return "AdminHome";
    }

    @GetMapping("/registerDoctor")
    public String showRegistrationForm(Model model){
        DoctorDTO doctor = new DoctorDTO();

        model.addAttribute("doctor", doctor);
        return "registerDoctor";
    }
    @Transactional
    @PostMapping("/registerDoctor")
    public String registerDonor(@ModelAttribute DoctorDTO registeredDoctor,
                                BindingResult result,
                                Model model){
        User existingUser = userService.findUserByUsername(registeredDoctor.getUsername());
        if(existingUser != null && existingUser.getUsername()!=null){
            result.rejectValue("username", "error with username",
                    "There is already an account registered with the same username");
        }

        Doctor newDoctor = new Doctor();
        newDoctor.setEmail(registeredDoctor.getEmail());
        newDoctor.setFirstName(registeredDoctor.getFirstName());
        newDoctor.setLastName(registeredDoctor.getLastName());
        newDoctor.setPassword(registeredDoctor.getPassword());
        newDoctor.setUsername(registeredDoctor.getUsername());
        newDoctor.setCNP(registeredDoctor.getCNP());
        newDoctor.setDonationCenter(donationCenterService.findDonationCenterByName(registeredDoctor.getLocation()));
        doctorService.saveDoctor(newDoctor);

        System.out.println("Doctor saved: " +  newDoctor.toString());

        return "redirect:/AdminHome";

    }
    @GetMapping("/deleteDoctor")
    public String deleteEmployee(@RequestParam Integer doctorId) {
        doctorService.deleteDoctorById(doctorId);
        return "redirect:/AdminHome";
    }

    @Transactional
    @PostMapping("/updateDoctor")
    public String updateDoctor(@ModelAttribute DoctorDTO updatedDoctor){
        doctorService.updateDoctor(updatedDoctor.getUsername(),
                                   updatedDoctor.getFirstName(),
                                   updatedDoctor.getLastName(),
                                   updatedDoctor.getEmail(),
                                   updatedDoctor.getCNP(),
                                   donationCenterService.findDonationCenterByName(updatedDoctor.getLocation()));
        return "redirect:/AdminHome";
    }

    @GetMapping("/updateDoctor")
    public ModelAndView showUpdateForm(@RequestParam Integer doctorId) {
        ModelAndView mav = new ModelAndView("updateDoctor");
        Doctor doctor = doctorService.findDoctorById(doctorId);
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setFirstName(doctor.getFirstName());
        doctorDTO.setLastName(doctor.getLastName());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setUsername(doctor.getUsername());
        doctorDTO.setCNP(doctor.getCNP());
        doctorDTO.setLocation(doctor.getDonationCenter().getName());
        mav.addObject("doctor", doctorDTO);
        return mav;
    }

}
