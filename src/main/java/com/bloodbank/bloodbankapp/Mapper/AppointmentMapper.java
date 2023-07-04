package com.bloodbank.bloodbankapp.Mapper;

import com.bloodbank.bloodbankapp.DTO.Request.AppointmentDTO;
import com.bloodbank.bloodbankapp.DTO.Request.DoctorAppointmentsDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public DoctorAppointmentsDTO appointmentsModelToDoctorAppointmentsDTO(Appoitment appoitment, Doctor doctor){
        DoctorAppointmentsDTO doctorAppointmentsDTO = new DoctorAppointmentsDTO();
        doctorAppointmentsDTO.setDoctor(doctor);
        doctorAppointmentsDTO.setDonor(appoitment.getDonor());
        doctorAppointmentsDTO.setDonationCenter(appoitment.getDonationCenter());
        if(appoitment.isConfirmed()){
            doctorAppointmentsDTO.setConfirmed("YES");
        }
        else{
            doctorAppointmentsDTO.setConfirmed("NO");
        }
        doctorAppointmentsDTO.setId(appoitment.getId());
        doctorAppointmentsDTO.setDate(appoitment.getDate().toString().substring(0,10));
        System.out.println(appoitment.getBloodTestResult());
        doctorAppointmentsDTO.setResult(appoitment.getBloodTestResult());
        return doctorAppointmentsDTO;
    }
    public AppointmentDTO entityToDto(Appoitment appoitment){
        String address = appoitment.getDonationCenter().getCounty() + ", " +
                appoitment.getDonationCenter().getCity() + ", " +
                appoitment.getDonationCenter().getStreet() + ", " +
                appoitment.getDonationCenter().getNumber();
        AppointmentDTO appoitmentDTO = new AppointmentDTO();
        appoitmentDTO.setId(appoitment.getId());
        appoitmentDTO.setAddress(address);
        appoitmentDTO.setDonationCenter(appoitment.getDonationCenter().getName());
        appoitmentDTO.setResult(appoitment.getBloodTestResult());
        System.out.println(appoitment.getBloodTestResult());
        if(appoitment.isConfirmed()){
            appoitmentDTO.setConfirmed("YES");
        }
        else{
            appoitmentDTO.setConfirmed("NO");
        }
        appoitmentDTO.setDate(appoitment.getDate().toString().substring(0,10));
        return appoitmentDTO;
    }

}
