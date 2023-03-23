package com.bloodbank.bloodbankapp.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appoitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "donation_center_id")
    private DonationCenter donationCenter;

    private boolean confirmed;

    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date date;

    // Constructors

    public Appoitment(Donor donor, DonationCenter donationCenter, boolean confirmed, Date date) {
        this.donor = donor;
        this.donationCenter = donationCenter;
        this.confirmed = confirmed;
        this.date = date;
    }

    public Appoitment(){}

    // Getters and setters

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public void setDonationCenter(DonationCenter donationCenter) {
        this.donationCenter = donationCenter;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public DonationCenter getDonationCenter() {
        return donationCenter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
