package com.bloodbank.bloodbankapp.Controller;
import com.bloodbank.bloodbankapp.DTO.Request.AppointmentDTO;
import com.bloodbank.bloodbankapp.DTO.Request.AppointmentRequestDTO;
import com.bloodbank.bloodbankapp.DTO.Response.MessageResponse;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Facade.AppointmentFacade;
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

    private final AppointmentFacade appointmentFacade;

    @PostMapping("/createAppointment")
    public ResponseEntity<?> makeAppointment(@RequestBody AppointmentRequestDTO appointment) throws ParseException {
        appointmentFacade.saveAppointment(appointment);
        return ResponseEntity.ok(new MessageResponse("Appointment registered successfully"));
    }

    @GetMapping("/getNonAvailableDays/{id}")
    public ResponseEntity<?> getNonAvailableDays(@PathVariable(value = "id") Integer id){
        List<Date> dates = appointmentFacade.getUnavailableDatesForLocation(id);
        return ResponseEntity.ok(dates);
    }

    @GetMapping("/allDoctorAppointments")
    public ResponseEntity<?> getDoctorAppointments(@RequestParam String username){
        List<Appoitment> allAppointments = appointmentFacade.findAllDoctorAppointments(username);
        return ResponseEntity.ok(allAppointments);
    }

    @GetMapping("/getCurrentDayAppointments/{username}/{date}")
    public ResponseEntity<?> getDoctorAppointmentsForCurrentDate(@PathVariable(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                                                 @PathVariable(value="username") String username){
        return ResponseEntity.ok(appointmentFacade.getDoctorCurrentDayAppointments(username,date));
    }

    @GetMapping("/currentAppointments")
    public ResponseEntity<List<AppointmentDTO>> getDonorCenters(@RequestParam String username){
        return ResponseEntity.ok(appointmentFacade.getDonorCurrentAppointments(username));
    }
    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable(value = "id") Integer id) {
        if(appointmentFacade.existsAppointment(id)) {
            appointmentFacade.deleteAppointment(id);
            return ResponseEntity.ok(new MessageResponse("Account deleted!"));
        }else{
            return ResponseEntity.badRequest().body(new MessageResponse("Appointment was not found!"));
        }
    }

    @PutMapping("/confirmAppointment/{id}")
    public ResponseEntity<?> confirmAppointment(@PathVariable(value="id") Integer id){
       appointmentFacade.confirmAppointment(id);
        return ResponseEntity.ok(new MessageResponse("Appointment confirmed"));
    }
    @GetMapping("/getBloodResults/{id}")
    public ResponseEntity<?> getBloodResults(@PathVariable(value="id")Integer id){
        return ResponseEntity.ok(appointmentFacade.getBloodResult(id));
    }

}
