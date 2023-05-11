package com.bloodbank.bloodbankapp.Facade;
import com.bloodbank.bloodbankapp.DTO.Request.DonationCenterRequestDTO;
import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import com.bloodbank.bloodbankapp.Mapper.DonationCenterMapper;
import com.bloodbank.bloodbankapp.Service.IDonationCenterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DonationCenterFacade {

    private final IDonationCenterService donationCenterService;
    private final DonationCenterMapper donationCenterMapper;

    public List<DonationCenterRequestDTO> getDonationCentersWithLabel(){
        List<DonationCenter> donationCenters = donationCenterService.findAllDonationCenters();
        List<DonationCenterRequestDTO> donationCenterRequestDTOS = new ArrayList<>();
        for(DonationCenter d: donationCenters){
            donationCenterRequestDTOS.add(donationCenterMapper.EntityToDtoRequest(d));
        }
        return donationCenterRequestDTOS;
    }
    public List<DonationCenter> getAllDonationCenters(){
        return donationCenterService.findAllDonationCenters();
    }
    public List<DonationCenter> findAllDonationCentersInCounty(String county){
        return donationCenterService.findAllDonationCentersInCounty(county);
    }
}
