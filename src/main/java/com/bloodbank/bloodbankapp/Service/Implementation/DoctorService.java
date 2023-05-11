package com.bloodbank.bloodbankapp.Service.Implementation;

import com.bloodbank.bloodbankapp.DTO.Request.DoctorDTO;
import com.bloodbank.bloodbankapp.Entity.*;
import com.bloodbank.bloodbankapp.Repository.AppoitmentRepository;
import com.bloodbank.bloodbankapp.Repository.DoctorRepository;
import com.bloodbank.bloodbankapp.Repository.DonationCenterRepository;
import com.bloodbank.bloodbankapp.Repository.UserRepository;
import com.bloodbank.bloodbankapp.Service.IDoctorService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class DoctorService implements IDoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final AppoitmentRepository appoitmentRepository;
    private final RoleService roleService;
    private final DonationCenterRepository donationCenterRepository;
    private final PasswordEncoder encoder;

    public void saveDoctor(Doctor doctor){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findRoleByName(ERole.DOCTOR));
        doctor.setRoles(roleSet);
        doctor.setPassword(encoder.encode(doctor.getPassword()));
        doctorRepository.save(doctor);
    }

    public void deleteDoctorById(Integer id){
        userRepository.delete(userRepository.findUserById(id));
    }

    public Doctor findDoctorById(Integer id){
        return doctorRepository.findDoctorById(id);
    }
    public List<Doctor> findAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor findDoctorByUsername(String username){
        return doctorRepository.findDoctorByUsername(username);
    }

    public boolean existDoctorById(Integer id){
        return doctorRepository.existsById(id);
    }

    @Transactional
    public void updateDoctor(DoctorDTO updatedDoctor){

        doctorRepository.updateDoctor(
                updatedDoctor.getId(),
                updatedDoctor.getUsername(),
                updatedDoctor.getFirstName(),
                updatedDoctor.getLastName(),
                updatedDoctor.getEmail(),
                updatedDoctor.getCnp(),
                donationCenterRepository.findDonationCenterByName(updatedDoctor.getLocation().getName()));
    }

    public Doctor findDoctorWithMinAppointments(DonationCenter donationCenter){
        List<Doctor> doctorList = findAllDoctors();
        if(doctorList.isEmpty()){
            return null;
        }
        Map.Entry<Doctor,Integer> min = null;
        Map<Doctor, Integer> doctorsAndNumberOfAppointments = new HashMap<>();
        for(Doctor doctor: doctorList) {
            List<Appoitment> appointments = appoitmentRepository.getAppoitmentsByDoctor(doctor);
            if(doctor.getDonationCenter().equals(donationCenter)) {
                doctorsAndNumberOfAppointments.put(doctor, appointments.size());
            }
        }
        for (Map.Entry<Doctor, Integer> entry : doctorsAndNumberOfAppointments.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min.getKey();
    }



}
