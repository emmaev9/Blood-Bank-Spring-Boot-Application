package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.DTO.Request.AppointmentRequestDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Repository.AppoitmentRepository;
import com.bloodbank.bloodbankapp.Repository.DoctorRepository;
import com.bloodbank.bloodbankapp.Repository.DonationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Dates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<Date> nonAvailableDatesForAppointments(DonationCenter donationCenter){
        Map<Date,Integer> map = new HashMap<>();
        List<Appoitment> appointmentList = appoitmentRepository.findAllByDonationCenter(donationCenter);
        List<Date> nonavailableDates = new ArrayList<>();
        for(Appoitment appoitment: appointmentList){
            if(map.containsKey(appoitment.getDate())){
                map.replace(appoitment.getDate(), map.get(appoitment.getDate())+1);
            }
            else{
                map.put(appoitment.getDate(), 1);
            }
        }
        for(Map.Entry<Date,Integer> entry : map.entrySet()){
            if(entry.getValue() > donationCenter.getMaximumDonations()){
                nonavailableDates.add(entry.getKey());
                System.out.println(entry.getKey());
            }
        }
        System.out.println(nonavailableDates.size());
        return nonavailableDates;
    }

    public Page<Appoitment> getAllAppointments(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return appoitmentRepository.findAll(pageable);
    }

}
