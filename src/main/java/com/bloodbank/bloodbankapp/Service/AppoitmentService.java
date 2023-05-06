package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.DTO.Request.AppointmentRequestDTO;
import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Repository.AppoitmentRepository;
import com.bloodbank.bloodbankapp.Repository.DonationCenterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@AllArgsConstructor
public class AppoitmentService {

    private final AppoitmentRepository appoitmentRepository;
    private final DonorService donorService;
    private final DoctorService doctorService;
    private final DonationCenterRepository donationCenterRepository;
    private final EmailService emailService;

    public void saveAppoitment(AppointmentRequestDTO appointmentDTO) throws ParseException {

        Appoitment newAppointment = new Appoitment();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = formatter.parse(appointmentDTO.getDate());
        Donor donor = donorService.findDonorByUsername(appointmentDTO.getUsername());
        Doctor doctor = doctorService.findDoctorWithMinAppointments();
        DonationCenter donationCenter = donationCenterRepository.findDonationCenterByName(appointmentDTO.getLocation().getName());

        newAppointment.setDate(date);
        newAppointment.setDonor(donor);
        newAppointment.setDonationCenter(donationCenter);
        newAppointment.setConfirmed(false);
        newAppointment.setDoctor(doctor);
        newAppointment.setReminderType(appointmentDTO.getReminderType());
        appoitmentRepository.save(newAppointment);

        String message = "Hello " + donor.getFirstName()+  ",\n" + "\nYou successfully registered for blood donation via BloodBank application!" +
                "\n\nWe are waiting for you at "+ donationCenter.getName() +", " + donationCenter.getCity()+".";
        emailService.sendEmail(donor.getEmail(), "Blood donation appointment", message);
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
            }
        }
        return nonavailableDates;
    }

    public Page<Appoitment> getAllAppointments(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return appoitmentRepository.findAll(pageable);
    }
    public List<Appoitment> findNextDayAppointments(){
        final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
        Date today = new Date();
        Date nextDay = new Date(today.getTime() + MILLIS_IN_A_DAY + MILLIS_IN_A_DAY + MILLIS_IN_A_DAY);
        Date date = setTimeToMidnight(nextDay);
        return appoitmentRepository.findAllByDate(date);
    }

    public Date setTimeToMidnight(Date date) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime( date );
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

}
