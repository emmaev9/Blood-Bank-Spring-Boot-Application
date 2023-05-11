package com.bloodbank.bloodbankapp.Facade;

import com.bloodbank.bloodbankapp.DTO.Request.DonorDTO;
import com.bloodbank.bloodbankapp.Entity.Donor;
import com.bloodbank.bloodbankapp.Mapper.DonorMapper;
import com.bloodbank.bloodbankapp.Service.IDonorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DonorFacade {
    private final IDonorService donorService;
    private final DonorMapper donorMapper;

    public void registerDonor(DonorDTO donorDTO){
        Donor newDonor = donorMapper.donorDTOToModel(donorDTO);
        donorService.saveDonor(newDonor);
    }
    public void updateDonor(DonorDTO donorDTO){
        Donor newDonor = donorMapper.donorDTOToModel(donorDTO);
        donorService.updateDonor(newDonor);
    }
    public void deleteDonor(Integer id){
        donorService.deleteDonor(id);
    }

}

