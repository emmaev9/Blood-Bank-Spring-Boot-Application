package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Repository.DonorRepository;
import com.bloodbank.bloodbankapp.Repository.RoleRepository;
import com.bloodbank.bloodbankapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DonorService {
    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public List<Donor> findAllDonors(){
        List<Donor> donors = donorRepository.findAll();
        return donors;
    }

    public void saveDonor(Donor donor){
        donor.setPassword(passwordEncoder.encode(donor.getPassword()));
        if(roleRepository.findRole("ROLE_USER")==null){
            Role role = new Role("ROLE_USER");
            roleRepository.save(role);
            donor.setRole(role);
        }
        else{
            donor.setRole(roleRepository.findRole("ROLE_USER"));
        }
        donorRepository.save(donor);
    }

    public Donor findDonorByUsername(String username){
        return donorRepository.findDonorByUsername(username);
    }

    public void deleteDonorById(Integer id){
        userRepository.delete(userRepository.findUserById(id));
    }

    @Transactional
    public void updateDonor(Donor updatedDonor){
         donorRepository.updateDonor(donorRepository.findDonorByUsername(updatedDonor.getUsername()).getId(),
                                            updatedDonor.getFirstName(), updatedDonor.getLastName(),
                                          updatedDonor.getEmail(), updatedDonor.getCounty());
    }
    public void deleteDonor(Donor donor){
        donorRepository.delete(donor);
    }

}
