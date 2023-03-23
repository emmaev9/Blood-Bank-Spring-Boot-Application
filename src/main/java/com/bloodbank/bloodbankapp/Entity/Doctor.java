package com.bloodbank.bloodbankapp.Entity;

import javax.persistence.*;

@Entity
public class Doctor extends User {

    @OneToOne
    @JoinColumn(name = "donation_center_id")
    private DonationCenter donationCenter;
    private String CNP;

    public DonationCenter getDonationCenter() {
        return donationCenter;
    }

    // Constructors

    public Doctor(String firstName, String lastName, String username, String email, String password) {
        super(firstName, lastName,username, email, password);
    }

    public Doctor(String CNP, String firstName, String lastName, String username, String email, String password,DonationCenter donationCenter) {
        super(firstName, lastName, username, email, password);
        this.donationCenter = donationCenter;
        this.CNP = CNP;
    }


    public Doctor() {}

    // Getters and setters


    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setDonationCenter(DonationCenter donationCenter) {
        this.donationCenter = donationCenter;
    }


}
