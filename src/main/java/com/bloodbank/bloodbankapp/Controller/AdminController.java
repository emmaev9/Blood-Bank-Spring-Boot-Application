package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.DoctorDTO;
import com.bloodbank.bloodbankapp.DTO.Response.MessageResponse;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Mapper.DoctorMapper;
import com.bloodbank.bloodbankapp.Service.DoctorService;
import com.bloodbank.bloodbankapp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/admin/")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @GetMapping("/adminHome")
    public String donorHomePage(){
        return "adminHome";
    }

    @PostMapping("/registerDoctor")
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorDTO registeredDoctor){
        if(userService.existsByUsername(registeredDoctor.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if(userService.existsByEmail(registeredDoctor.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        Doctor newDoctor = doctorMapper.doctorDTOtoModel(registeredDoctor);
        newDoctor.setPassword(encoder.encode(newDoctor.getPassword()));
        doctorService.saveDoctor(newDoctor );
        return ResponseEntity.ok(new MessageResponse("Doctor registered!"));
    }

    @DeleteMapping("/deleteDoctor/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable(value = "id") Integer id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.ok(new MessageResponse("Doctor deleted"));
    }

    @Transactional
    @PostMapping("/editDoctorProfile")
    public ResponseEntity<?> editProfileForm(@RequestBody DoctorDTO updatedDoctor){
        Integer id = updatedDoctor.getId();
        if(userService.findUserByUsername(updatedDoctor.getUsername()) != null
                && userService.findUserById(id)!=userService.findUserByUsername(updatedDoctor.getUsername()) ){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if(userService.findUserByEmail(updatedDoctor.getEmail()) != null
                && userService.findUserById(id)!=userService.findUserByEmail(updatedDoctor.getEmail()) ){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        doctorService.updateDoctor(updatedDoctor);
        return ResponseEntity.ok(new MessageResponse("Profile updated!"));
    }
}
