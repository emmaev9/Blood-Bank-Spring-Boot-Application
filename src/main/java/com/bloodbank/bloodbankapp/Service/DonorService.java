package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Entity.ERole;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Repository.DonorRepository;
import com.bloodbank.bloodbankapp.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DonorService {

    private DonorRepository donorRepository;
    private RoleService roleService;
    private UserRepository userRepository;

    public void saveDonor(Donor donor){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.findRoleByName(ERole.DONOR));
        donor.setRoles(roleSet);
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
                                          updatedDonor.getEmail(), updatedDonor.getCounty(),
                                          updatedDonor.getPassword(), updatedDonor.getUsername(),
                                          updatedDonor.getBloodType(),
                                          updatedDonor.getPhoneNumber());
    }
}
