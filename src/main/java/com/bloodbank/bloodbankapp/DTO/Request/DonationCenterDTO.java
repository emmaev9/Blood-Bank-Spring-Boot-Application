package com.bloodbank.bloodbankapp.DTO.Request;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DonationCenterDTO {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String county;
    @NonNull
    private String city;
    @NonNull
    private String street;
    @NonNull
    private String number;
    @NonNull
    private boolean functional;
    @NonNull
    private String schedule;
    @NonNull
    private Integer maximumDonations;
}
