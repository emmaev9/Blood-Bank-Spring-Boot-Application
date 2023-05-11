package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.DTO.Request.DoctorDTO;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;

import java.util.*;

public interface IDoctorService {
    void saveDoctor(Doctor doctor);
    void deleteDoctorById(Integer id);
    Doctor findDoctorById(Integer id);
    List<Doctor> findAllDoctors();
    Doctor findDoctorByUsername(String username);
    boolean existDoctorById(Integer id);
    void updateDoctor(DoctorDTO updatedDoctor);
    Doctor findDoctorWithMinAppointments(DonationCenter donationCenter);
}
