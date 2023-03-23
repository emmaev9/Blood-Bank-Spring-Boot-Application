package com.bloodbank.bloodbankapp.DTO;

import org.springframework.format.annotation.DateTimeFormat;

public class AppoitmentDTO {
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private String date;
    private String location;

    public AppoitmentDTO(String location, String date){
        this.date = date;
        this.location = location;
    }
    public AppoitmentDTO(){}
    @DateTimeFormat(pattern="dd-MM-yyyy")
    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
