package com.bloodbank.bloodbankapp.Mapper;

import com.bloodbank.bloodbankapp.DTO.DoctorDTO;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public DoctorDTO doctorModelToDTO(Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setUsername(doctor.getUsername());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setLocation(doctor.getDonationCenter());
        doctorDTO.setLastName(doctor.getLastName());
        doctorDTO.setFirstName(doctor.getFirstName());
        doctorDTO.setId(doctor.getId());
        doctorDTO.setCnp(doctor.getCNP());
        return  doctorDTO;
    }

}
