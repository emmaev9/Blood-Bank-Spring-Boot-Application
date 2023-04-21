package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.DoctorDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Mapper.DoctorMapper;
import com.bloodbank.bloodbankapp.Service.AppoitmentService;
import com.bloodbank.bloodbankapp.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/doctor/")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppoitmentService appoitmentService;

    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping("/doctorHome")
    public String doctorHome(){
        return "DoctorHome";
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
        List<Appoitment> appointments = appoitmentService.findAllDoctorAppointments(doctor);
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
}
