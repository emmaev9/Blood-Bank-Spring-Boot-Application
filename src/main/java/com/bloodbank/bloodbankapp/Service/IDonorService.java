package com.bloodbank.bloodbankapp.Service;

import com.bloodbank.bloodbankapp.Entity.Donor;

public interface IDonorService {
    void saveDonor(Donor donor);
    Donor findDonorByUsername(String username);
    void updateDonor(Donor updatedDonor);
    void deleteDonor(Integer donorId);
}
