package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.DoctorDTO;
import com.bloodbank.bloodbankapp.DTO.PaginationDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Mapper.DoctorMapper;
import com.bloodbank.bloodbankapp.Service.AppoitmentService;
import com.bloodbank.bloodbankapp.Service.DoctorService;
import com.bloodbank.bloodbankapp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/doctor/")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final AppoitmentService appointmentService;
    private final DoctorMapper doctorMapper;

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
}
