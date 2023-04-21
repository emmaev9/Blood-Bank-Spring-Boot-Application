package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.AppointmentDTO;
import com.bloodbank.bloodbankapp.DTO.Request.AppointmentRequestDTO;
import com.bloodbank.bloodbankapp.DTO.Response.MessageResponse;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Repository.AppoitmentRepository;
import com.bloodbank.bloodbankapp.Service.AppoitmentService;
import com.bloodbank.bloodbankapp.Service.DoctorService;
import com.bloodbank.bloodbankapp.Service.DonationCenterService;
import com.bloodbank.bloodbankapp.Service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/appointment/")
public class AppointmentController {

    @Autowired
    DonorService donorService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    AppoitmentService appointmentService;
    @Autowired
    DonationCenterService donationCenterService;
    @Autowired
    AppoitmentRepository appoitmentRepository;

    @PostMapping("/createAppointment")
    public ResponseEntity<?> makeAppointment(@RequestBody AppointmentRequestDTO appointment) throws ParseException {
        appointmentService.saveAppoitment(appointment);
        return ResponseEntity.ok(new MessageResponse("Appointment registered successfully"));
    }

    @GetMapping("/allDoctorAppointments")
    public ResponseEntity<?> getDoctorAppointments(@RequestParam String username){
        Doctor currentDoctor = doctorService.findDoctorByUsername(username);
        List<Appoitment> allAppointments = appointmentService.findAllDoctorAppointments(currentDoctor);
        return ResponseEntity.ok(allAppointments);
    }

    @GetMapping("/currentAppointments")
    public ResponseEntity<List<AppointmentDTO>> getDonorCenters(@RequestParam String username){
        Donor currentDonor = donorService.findDonorByUsername(username);
        List<Appoitment> allAppointments = appointmentService.findUserAppoitments(currentDonor);
        List<AppointmentDTO> requestedAppointments = new ArrayList<>();
        for(Appoitment appoitment: allAppointments){
            String address = appoitment.getDonationCenter().getCounty() + ", " +
                             appoitment.getDonationCenter().getCity() + ", " +
                             appoitment.getDonationCenter().getStreet() + ", " +
                             appoitment.getDonationCenter().getNumber();
            AppointmentDTO appoitmentDTO = new AppointmentDTO();
            appoitmentDTO.setId(appoitment.getId());
            appoitmentDTO.setAddress(address);
            appoitmentDTO.setDonationCenter(appoitment.getDonationCenter().getName());
            if(appoitment.isConfirmed()){
                appoitmentDTO.setConfirmed("Confirmed");
            }
            else{
                appoitmentDTO.setConfirmed("Not confirmed");
            }
            appoitmentDTO.setDate(appoitment.getDate().toString().substring(0,10));
            System.out.println(appoitmentDTO.getAddress() + "  " + appoitmentDTO.getDonationCenter() + "  " + appoitmentDTO.getDate() + "  " +appoitmentDTO.getConfirmed());
            requestedAppointments.add(appoitmentDTO);
        }
        return ResponseEntity.ok(requestedAppointments);
    }
    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<?> deleteDonor(@PathVariable(value = "id") Integer id) {
        appointmentService.deleteAppointmentById(id);
        return ResponseEntity.ok(new MessageResponse("Account deleted!"));
    }

    @PutMapping("/confirmAppointment/{id}")
    public ResponseEntity<?> confirmAppointment(@PathVariable(value="id") Integer id){
        Appoitment appoitment = appointmentService.findById(id);
        appoitment.setConfirmed(true);
        appoitmentRepository.save(appoitment);
        return ResponseEntity.ok(new MessageResponse("Appointment confirmed"));
    }

}
