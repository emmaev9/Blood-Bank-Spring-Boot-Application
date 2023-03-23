package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Appoitment;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Repository.AppoitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppoitmentService {
    @Autowired
    private AppoitmentRepository appoitmentRepository;

    public void saveAppoitment(Appoitment appoitment){
        appoitmentRepository.save(appoitment);
    }
    public List<Appoitment> findUserAppoitments(Donor donor){
        return appoitmentRepository.findUserAppoitments(donor);
    }
    public void deleteUserAppoitments(Donor donor) {
        List<Appoitment> userAppoitments = appoitmentRepository.findUserAppoitments(donor);
        appoitmentRepository.deleteAll(userAppoitments);
    }
}
