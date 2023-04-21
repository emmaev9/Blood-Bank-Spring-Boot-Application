package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.DTO.Request.AppointmentRequestDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Repository.AppoitmentRepository;
import com.bloodbank.bloodbankapp.Repository.DoctorRepository;
import com.bloodbank.bloodbankapp.Repository.DonationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class AppoitmentService {
    @Autowired
    private AppoitmentRepository appoitmentRepository;
    @Autowired
    private DonorService donorService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DonationCenterRepository donationCenterRepository;

    public void saveAppoitment(AppointmentRequestDTO appointmentDTO) throws ParseException {
        Appoitment newAppointment = new Appoitment();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = formatter.parse(appointmentDTO.getDate());

        newAppointment.setDate(date);
        newAppointment.setDonor(donorService.findDonorByUsername(appointmentDTO.getUsername()));
        newAppointment.setDonationCenter(donationCenterRepository.findDonationCenterByName(appointmentDTO.getLocation().getName()));
        newAppointment.setConfirmed(false);

        Doctor doctor = doctorService.findDoctorWithMinAppointments();
        newAppointment.setDoctor(doctor);
        appoitmentRepository.save(newAppointment);
    }
    public List<Appoitment> findUserAppoitments(Donor donor){
        return appoitmentRepository.findUserAppoitments(donor);
    }
    public void deleteUserAppoitments(Donor donor) {
        List<Appoitment> userAppoitments = appoitmentRepository.findUserAppoitments(donor);
        appoitmentRepository.deleteAll(userAppoitments);
    }
    public void deleteAppointmentById(Integer id){
        appoitmentRepository.deleteById(id);
    }
    public List<Appoitment> findAllDoctorAppointments(Doctor doctor){
        return appoitmentRepository.getAppoitmentByDoctor(doctor);
    }
    public Appoitment findById(Integer id){
        return appoitmentRepository.findAppoitmentById(id);
    }
}
