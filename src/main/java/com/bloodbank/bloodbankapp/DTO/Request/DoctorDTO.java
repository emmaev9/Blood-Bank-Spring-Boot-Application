package com.bloodbank.bloodbankapp.DTO.Request;

import com.bloodbank.bloodbankapp.Entity.DonationCenter;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    private String cnp;
    @NonNull
    private DonationCenter location;
    @NonNull
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String username;
    @NonNull
    private String email;

    private String password;
}
