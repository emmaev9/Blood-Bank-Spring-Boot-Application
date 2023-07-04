package com.bloodbank.bloodbankapp.DTO.Request;

import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.Donor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAppointmentsDTO {
    private Integer id;
    private Donor donor;
    private DonationCenter donationCenter;
    private Doctor doctor;
    private String confirmed;
    private String date;
    private String result;
}
