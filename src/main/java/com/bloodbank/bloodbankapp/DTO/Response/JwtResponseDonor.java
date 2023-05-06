package com.bloodbank.bloodbankapp.DTO.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class JwtResponseDonor {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String password;
    private String username;
    private String email;
    private String county;
    private String firstName;
    private String lastName;
    private String bloodType;
    private String phoneNumber;
    private List<String> roles;


    public JwtResponseDonor(String accessToken,String password, Integer id, String username, String email, List<String> roles,String firstName, String lastName, String bloodType, String county, String phoneNumber) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bloodType = bloodType;
        this.county = county;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
