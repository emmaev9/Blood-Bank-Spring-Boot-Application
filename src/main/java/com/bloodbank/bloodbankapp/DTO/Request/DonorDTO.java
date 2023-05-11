package com.bloodbank.bloodbankapp.DTO.Request;

import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DonorDTO {
    private String bloodType;
    @NonNull
    private String county;
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
    @NonNull
    private String password;
    @NonNull
    private String phoneNumber;
}
