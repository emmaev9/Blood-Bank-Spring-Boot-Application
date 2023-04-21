package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Entity.ERole;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Repository.DonorRepository;
import com.bloodbank.bloodbankapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DonorService {
    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    public List<Donor> findAllDonors(){
        return donorRepository.findAll();
    }

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
                                          updatedDonor.getBloodType());
    }
    public void deleteDonor(Donor donor){
        donorRepository.delete(donor);
    }

}
