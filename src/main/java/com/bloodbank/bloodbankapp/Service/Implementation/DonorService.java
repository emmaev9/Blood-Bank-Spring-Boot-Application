package com.bloodbank.bloodbankapp.Service.Implementation;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Entity.ERole;
import com.bloodbank.bloodbankapp.Entity.Role;
import com.bloodbank.bloodbankapp.Repository.AppoitmentRepository;
import com.bloodbank.bloodbankapp.Repository.DonorRepository;
import com.bloodbank.bloodbankapp.Repository.RoleRepository;
import com.bloodbank.bloodbankapp.Service.IDonorService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class DonorService implements IDonorService {

    private DonorRepository donorRepository;
    private AppoitmentRepository appoitmentRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    public void saveDonor(Donor donor){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findByName(ERole.DONOR));
        donor.setRoles(roleSet);
        donor.setPassword(encoder.encode(donor.getPassword()));
        donorRepository.save(donor);
    }

    public Donor findDonorByUsername(String username){
        return donorRepository.findDonorByUsername(username);
    }

    @Transactional
    public void updateDonor(Donor updatedDonor){
         updatedDonor.setPassword(encoder.encode(updatedDonor.getPassword()));
         donorRepository.updateDonor(donorRepository.findDonorByUsername(updatedDonor.getUsername()).getId(),
                                          updatedDonor.getFirstName(), updatedDonor.getLastName(),
                                          updatedDonor.getEmail(), updatedDonor.getCounty(),
                                          updatedDonor.getPassword(), updatedDonor.getUsername(),
                                          updatedDonor.getBloodType(),
                                          updatedDonor.getPhoneNumber());
    }
    public void deleteDonor(Integer donorId){
        Donor donor = donorRepository.findDonorById(donorId);
        List<Appoitment> donorAppointments = appoitmentRepository.findAppoitmentsByDonor_Username(donor.getUsername());
        appoitmentRepository.deleteAll(donorAppointments);
        donorRepository.delete(donor);
    }
}
