package com.bloodbank.bloodbankapp.Mapper;

import com.bloodbank.bloodbankapp.DTO.Request.DoctorAppointmentsDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public DoctorAppointmentsDTO appointmentsModelToDoctorAppointmentsDTO(Appoitment appoitment, Doctor doctor){
        DoctorAppointmentsDTO doctorAppointmentsDTO = new DoctorAppointmentsDTO();
        doctorAppointmentsDTO.setDoctor(doctor);
        doctorAppointmentsDTO.setDonor(appoitment.getDonor());
        doctorAppointmentsDTO.setDonationCenter(appoitment.getDonationCenter());
        if(appoitment.isConfirmed()){
            doctorAppointmentsDTO.setConfirmed("Confirmed");
        }
        else{
            doctorAppointmentsDTO.setConfirmed("Not confirmed");
        }
        doctorAppointmentsDTO.setId(appoitment.getId());
        doctorAppointmentsDTO.setDate(appoitment.getDate().toString().substring(0,10));
        return doctorAppointmentsDTO;
    }

}
