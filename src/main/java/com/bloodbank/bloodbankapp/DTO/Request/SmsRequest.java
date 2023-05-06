package com.bloodbank.bloodbankapp.DTO.Request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SmsRequest {
    @NonNull
    private final String phoneNumber; //destination phone number

    @NonNull
    private final String message;

    public SmsRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
