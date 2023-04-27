package com.bloodbank.bloodbankapp.DTO.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationCenterRequestDTO {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String county;
    @NonNull
    private String city;
    @NonNull
    private String label;

}
