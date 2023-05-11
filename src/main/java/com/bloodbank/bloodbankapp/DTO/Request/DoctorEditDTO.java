package com.bloodbank.bloodbankapp.DTO.Request;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorEditDTO {
    @NonNull
    private Integer id;
    @NonNull
    private String cnp;
    @NonNull
    private DonationCenter location;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String username;
    @NonNull
    private String email;
}