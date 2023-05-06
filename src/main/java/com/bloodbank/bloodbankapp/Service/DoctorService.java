package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.DTO.DoctorDTO;
import com.bloodbank.bloodbankapp.Entity.*;
import com.bloodbank.bloodbankapp.Repository.AppoitmentRepository;
import com.bloodbank.bloodbankapp.Repository.DoctorRepository;
import com.bloodbank.bloodbankapp.Repository.DonationCenterRepository;
import com.bloodbank.bloodbankapp.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final AppoitmentRepository appoitmentRepository;
    private final RoleService roleService;
    private final DonationCenterRepository donationCenterRepository;

    public void saveDoctor(Doctor doctor){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findRoleByName(ERole.DOCTOR));
        doctor.setRoles(roleSet);
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

    public Doctor findDoctorWithMinAppointments(){
        List<Doctor> doctorList = findAllDoctors();
        if(doctorList.isEmpty()){
            return null;
        }
        if(doctorList.size() == 1){
            return doctorList.get(0);
        }
        Map.Entry<Doctor,Integer> min = null;
        Map<Doctor, Integer> doctorsAndNumberOfAppointments = new HashMap<>();
        for(Doctor doctor: doctorList){
            List<Appoitment> appointments = appoitmentRepository.getAppoitmentByDoctor(doctor);
            doctorsAndNumberOfAppointments.put(doctor, appointments.size());
        }
        for (Map.Entry<Doctor, Integer> entry : doctorsAndNumberOfAppointments.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        return min.getKey();
    }



}
