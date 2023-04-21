package com.bloodbank.bloodbankapp.DTO.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class JwtResponseDoctor {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String password;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;
    private String cnp;
    private String location;

    public JwtResponseDoctor(String accessToken, Integer id, String username, String email, List<String> roles,String firstName, String lastName, String location, String CNP) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cnp = CNP;
        this.location = location;
    }
}
