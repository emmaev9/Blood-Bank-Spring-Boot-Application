package com.bloodbank.bloodbankapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "donor")
public class Donor extends User{
    private String bloodType;

    @Column(nullable = false)
    private String county;

    @Column(nullable = false)
    private String phoneNumber;

    public Donor(String firstName, String lastName, String email, String username, String password){
        super(firstName, lastName, username, email, password);
    }

}
