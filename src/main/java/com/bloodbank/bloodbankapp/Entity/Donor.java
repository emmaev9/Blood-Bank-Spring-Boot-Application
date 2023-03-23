package com.bloodbank.bloodbankapp.Entity;

import javax.persistence.*;

@Entity
public class Donor extends User{

    @Column(nullable = false)
    private String bloodType;

    private String county;

    //Constructors

    public Donor(String firstName, String lastName,String email, String username, String password,
                 String bloodType, String county) {
        super(firstName, lastName, username, email, password);
        this.bloodType = bloodType;
        this.county = county;
    }

    public Donor(String firstName, String lastName, String email, String username, String password){
        super(firstName, lastName, username, email, password);
    }

    public Donor() {}


    // Getters and setters

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "bloodType='" + bloodType + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
