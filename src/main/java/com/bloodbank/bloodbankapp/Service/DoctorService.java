package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Doctor;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Repository.DoctorRepository;
import com.bloodbank.bloodbankapp.Repository.RoleRepository;
import com.bloodbank.bloodbankapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveDoctor(Doctor doctor){
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        if(roleRepository.findRole("ROLE_DOCTOR")==null){
            Role role = new Role("ROLE_DOCTOR");
            roleRepository.save(role);
            doctor.setRole(role);
        }
        else{
            doctor.setRole(roleRepository.findRole("ROLE_DOCTOR"));
        }
        doctorRepository.save(doctor);
    }

    public void deleteDoctorByUsername(String username){
        userRepository.delete(userRepository.findUserByUsername(username));
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

    public Doctor findDoctorByName(String firstname, String lastname){
        return doctorRepository.findDoctorByName(firstname, lastname);
    }
    public Doctor findDoctorByUsername(String username){
        return doctorRepository.findDoctorByUsername(username);
    }

    public void updateDoctor(String username, String firstName, String lastName, String email, String CNP, DonationCenter donationCenter ){
        doctorRepository.updateDoctor(username,firstName,lastName,email,CNP, donationCenter);
    }



}
