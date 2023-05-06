package com.bloodbank.bloodbankapp.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDonorRequestDTO {
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
    private String bloodType;

    @NonNull
    private String county;

    @NonNull
    private String phoneNumber;
}
