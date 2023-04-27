package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.DoctorDTO;
import com.bloodbank.bloodbankapp.DTO.PaginationDTO;
import com.bloodbank.bloodbankapp.DTO.Response.MessageResponse;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Mapper.DoctorMapper;
import com.bloodbank.bloodbankapp.Service.AppoitmentService;
import com.bloodbank.bloodbankapp.Service.DoctorService;
import com.bloodbank.bloodbankapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/doctor/")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppoitmentService appointmentService;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/doctorHome")
    public ResponseEntity<?> doctorHome(){
        return ResponseEntity.ok(findPaginated(1));
    }

    @GetMapping("/page/{pageNo}")
    public ResponseEntity<?> findPaginated( @PathVariable(value = "pageNo") int pageNo) {
        int pageSize = 5;
        Page<Appoitment> page = appointmentService.getAllAppointments(pageNo, pageSize);
        List<Appoitment> listAppointment = page.getContent();
        PaginationDTO paginationDTO = new PaginationDTO(pageNo, page.getTotalPages(), page.getTotalElements(), listAppointment);
        System.out.println(listAppointment);
        System.out.println(pageNo);
        System.out.println(page.getTotalPages());
        return ResponseEntity.ok(paginationDTO);
    }

    @GetMapping("/getDoctorById")
    public ResponseEntity<?>getDoctor(@RequestParam(value="id") Integer id){
        Doctor doctor = doctorService.findDoctorById(id);
        DoctorDTO doctorDTO = doctorMapper.doctorModelToDTO(doctor);
        return ResponseEntity.ok(doctorDTO);
    }

    @GetMapping("/getDoctorAppointments")
    public ResponseEntity<?>getDoctorAppointments(@RequestParam(value = "username")String username){
        Doctor doctor = doctorService.findDoctorByUsername(username);
        List<Appoitment> appointments = appointmentService.findAllDoctorAppointments(doctor);
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/getAllDoctors")
    public ResponseEntity<?> getAllDoctors(){
        List<Doctor> doctorList = doctorService.findAllDoctors();
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        for(Doctor doc : doctorList){
            DoctorDTO doctorDTO = doctorMapper.doctorModelToDTO(doc);
            doctorDTOList.add(doctorDTO);
        }
        return ResponseEntity.ok(doctorDTOList);
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
