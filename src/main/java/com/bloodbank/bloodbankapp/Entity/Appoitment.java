package com.bloodbank.bloodbankapp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appoitment")
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

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private boolean confirmed;

    private Date date;

    private String reminderType;


    // Constructors

    public Appoitment(Donor donor, Doctor doctor, DonationCenter donationCenter, boolean confirmed, Date date, String reminderType) {
        this.donor = donor;
        this.doctor = doctor;
        this.donationCenter = donationCenter;
        this.confirmed = confirmed;
        this.date = date;
        this.reminderType = reminderType;
    }

}
