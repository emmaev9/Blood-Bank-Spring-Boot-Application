package com.bloodbank.bloodbankapp.DTO.Request;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDTO {
    @NonNull
    private String date;
    @NonNull
    private DonationCenter location;
    @NonNull
    private String username;

}
