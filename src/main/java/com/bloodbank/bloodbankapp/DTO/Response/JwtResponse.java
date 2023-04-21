package com.bloodbank.bloodbankapp.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter

public class JwtResponse {
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
    private List<String> roles;
    private String CNP;
    private String location;


    public JwtResponse(String accessToken,String password, Integer id, String username, String email, List<String> roles,String firstName, String lastName, String bloodType, String county) {
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
    }
    public JwtResponse(String accessToken,Integer id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
    public JwtResponse(String accessToken, Integer id, String username, String email, List<String> roles,String firstName, String lastName, String location, String CNP) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.location = location;
    }

}
