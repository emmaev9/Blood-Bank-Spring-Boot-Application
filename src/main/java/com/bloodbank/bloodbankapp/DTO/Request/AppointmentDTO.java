package com.bloodbank.bloodbankapp.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Integer id;
    private String address;
    private String donationCenter;
    private String confirmed;
    private String date;
    private String result;
}
