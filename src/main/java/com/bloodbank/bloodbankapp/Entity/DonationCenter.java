package com.bloodbank.bloodbankapp.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donationcenter")
public class DonationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private String county;

    private String city;
    private String street;
    private String number;

    @Column(nullable = false, updatable = false)
    private boolean functional;

    @Column(nullable = false, updatable = false)
    private String schedule;

    @Column(nullable = false, updatable = false)
    private Integer maximumDonations;

    // Constructors
    public DonationCenter(){}

    public DonationCenter(String name, String county, String city, String street, String number,
                          boolean functional, String schedule, Integer maximumDonations) {
        this.name = name;
        this.county = county;
        this.city = city;
        this.street = street;
        this.number = number;
        this.functional = functional;
        this.schedule = schedule;
        this.maximumDonations = maximumDonations;
    }

    // Getters and setters

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isFunctional() {
        return functional;
    }

    public void setFunctional(boolean functional) {
        this.functional = functional;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaximumDonations() {
        return maximumDonations;
    }

    public void setMaximumDonations(Integer maximumDonations) {
        this.maximumDonations = maximumDonations;
    }
}
