package com.bloodbank.bloodbankapp.Controller;

import com.bloodbank.bloodbankapp.DTO.Request.BloodResultsDTO;
import com.bloodbank.bloodbankapp.DTO.Request.DoctorDTO;
import com.bloodbank.bloodbankapp.DTO.Request.PaginationDTO;
import com.bloodbank.bloodbankapp.DTO.Response.MessageResponse;
import com.bloodbank.bloodbankapp.Facade.DoctorFacade;
import com.bloodbank.bloodbankapp.Facade.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/doctor/")
@AllArgsConstructor
public class DoctorController {

    private final DoctorFacade doctorFacade;
    private final UserFacade userFacade;

    @GetMapping("doctorHome/{id}")
    public ResponseEntity<?> doctorHome(@PathVariable(value="id") Integer id){
        return ResponseEntity.ok(findPaginated(1, id));
    }

    @GetMapping("page/{pageNo}/{id}")
    public ResponseEntity<?> findPaginated( @PathVariable(value = "pageNo") int pageNo,
                                            @PathVariable(value="id") Integer id) {
        System.out.println("A ajuns");
        PaginationDTO paginationDTO = doctorFacade.getPagination(pageNo,id);
        System.out.println(paginationDTO);
        System.out.println("A iesit");
        return ResponseEntity.ok(paginationDTO);
    }

    @GetMapping("/getDoctorById")
    public ResponseEntity<?>getDoctor(@RequestParam(value="id") Integer id){
        if(doctorFacade.existsDoctorById(id)) {
            return ResponseEntity.ok(doctorFacade.getDoctorById(id));
        }else{
            return ResponseEntity.badRequest().body(new MessageResponse("Doctor with id " + id + " was not found"));
        }
    }
    @Transactional
    @PostMapping("/sendBloodTestResults")
    public ResponseEntity<?>sendResults(@RequestBody BloodResultsDTO bloodResultsDTO){
        System.out.println(bloodResultsDTO.getResult());
        System.out.println(bloodResultsDTO.getAppointmentId());
        doctorFacade.sendResults(bloodResultsDTO);
        return ResponseEntity.ok(new MessageResponse("Results sent!"));
    }

    @GetMapping("/getDoctorAppointments")
    public ResponseEntity<?>getDoctorAppointments(@RequestParam(value = "username")String username){
        return ResponseEntity.ok(doctorFacade.getDoctorAppointments(username));
    }

    @GetMapping("/getAllDoctors")
    public ResponseEntity<?> getAllDoctors(){
        return ResponseEntity.ok(doctorFacade.getListOfDoctors());
    }

    @PostMapping("/registerDoctor")
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorDTO registeredDoctor){
        if(userFacade.existsUserByEmail(registeredDoctor.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if(userFacade.existsUserByEmail(registeredDoctor.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        doctorFacade.saveDoctor(registeredDoctor);
        return ResponseEntity.ok(new MessageResponse("Doctor registered!"));
    }

    @DeleteMapping("/deleteDoctor/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable(value = "id") Integer id) {
        if(doctorFacade.existsDoctorById(id)) {
            doctorFacade.deleteDoctorById(id);
            return ResponseEntity.ok(new MessageResponse("Doctor deleted"));
        }else{
            return ResponseEntity.badRequest().body(new MessageResponse("Doctor with id " + id + " was not found"));
        }
    }

    @Transactional
    @PostMapping("/editDoctorProfile")
    public ResponseEntity<?> editProfileForm(@RequestBody DoctorDTO updatedDoctor){
        Integer id = updatedDoctor.getId();
        if(userFacade.findUserByUsername(updatedDoctor.getUsername()) != null
                && userFacade.findUserById(id)!=userFacade.findUserByUsername(updatedDoctor.getUsername()) ){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if(userFacade.findUserByEmail(updatedDoctor.getEmail()) != null
                && userFacade.findUserById(id)!=userFacade.findUserByEmail(updatedDoctor.getEmail()) ){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        doctorFacade.updateDoctor(updatedDoctor);
        return ResponseEntity.ok(new MessageResponse("Profile updated!"));
    }
}
