package com.bloodbank.bloodbankapp.DTO.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class JwtResponseAdmin {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String password;
    private String username;
    private String email;
    private List<String> roles;
    public JwtResponseAdmin(String accessToken,Integer id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
