package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.AppointmentDTO;
import com.bloodbank.bloodbankapp.DTO.Request.AppointmentRequestDTO;
import com.bloodbank.bloodbankapp.DTO.Request.DoctorAppointmentsDTO;
import com.bloodbank.bloodbankapp.DTO.Response.MessageResponse;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Mapper.AppointmentMapper;
import com.bloodbank.bloodbankapp.Repository.AppoitmentRepository;
import com.bloodbank.bloodbankapp.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/appointment/")
@AllArgsConstructor
public class AppointmentController {

    private final DonorService donorService;
    private final DoctorService doctorService;
    private final AppoitmentService appointmentService;
    private final DonationCenterService donationCenterService;
    private final AppoitmentRepository appoitmentRepository;
    private final AppointmentMapper appointmentMapper;

    @PostMapping("/createAppointment")
    public ResponseEntity<?> makeAppointment(@RequestBody AppointmentRequestDTO appointment) throws ParseException {
        System.out.println(appointment.getReminderType());
        appointmentService.saveAppoitment(appointment);
        return ResponseEntity.ok(new MessageResponse("Appointment registered successfully"));
    }

    @GetMapping("/getNonAvailableDays/{id}")
    public ResponseEntity<?> getNonAvailableDays(@PathVariable(value = "id") Integer id){
        DonationCenter location = donationCenterService.findDonationCenterById(id);
        List<Date> dates = appointmentService.nonAvailableDatesForAppointments(location);
        for(Date d: dates){
            System.out.println(d.toString());
        }
        if(dates.isEmpty()){
            System.out.println("Empty list of dates");
        }
        return ResponseEntity.ok(dates);
    }

    @GetMapping("/allDoctorAppointments")
    public ResponseEntity<?> getDoctorAppointments(@RequestParam String username){
        Doctor currentDoctor = doctorService.findDoctorByUsername(username);
        List<Appoitment> allAppointments = appointmentService.findAllDoctorAppointments(currentDoctor);
        return ResponseEntity.ok(allAppointments);
    }

    @GetMapping("/getCurrentDayAppointments/{username}/{date}")
    public ResponseEntity<?> getDoctorAppointmentsForCurrentDate(@PathVariable(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                                                 @PathVariable(value="username") String username){
        Doctor currentDoctor = doctorService.findDoctorByUsername(username);
        List<Appoitment> allAppointments = appointmentService.findAllDoctorAppointments(currentDoctor);
        List<Appoitment> currentAppointments = new ArrayList<>();

        for(Appoitment appoitment: allAppointments){
            if(appoitment.getDate().compareTo(appointmentService.setTimeToMidnight(date))==0){
                System.out.println(appointmentService.setTimeToMidnight(appoitment.getDate()));
                currentAppointments.add(appoitment);
            }
        }
        List<DoctorAppointmentsDTO> doctorAppointmentsDTOList = new ArrayList<>();
        for(Appoitment appoitment: currentAppointments){
            doctorAppointmentsDTOList.add( appointmentMapper.appointmentsModelToDoctorAppointmentsDTO(appoitment, currentDoctor));
        }

        return ResponseEntity.ok(doctorAppointmentsDTOList);
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
                appoitmentDTO.setConfirmed("YES");
            }
            else{
                appoitmentDTO.setConfirmed("NO");
            }
            appoitmentDTO.setDate(appoitment.getDate().toString().substring(0,10));
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
