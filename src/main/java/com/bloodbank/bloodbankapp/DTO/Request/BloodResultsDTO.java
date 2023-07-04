package com.bloodbank.bloodbankapp.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloodResultsDTO {
    private Integer appointmentId;
    private String result;
}
