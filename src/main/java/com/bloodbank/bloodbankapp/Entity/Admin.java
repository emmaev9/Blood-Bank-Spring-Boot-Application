package com.bloodbank.bloodbankapp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends User{

    public Admin(String firstName, String lastName, String username, String email, String password) {
        super(firstName, lastName,username, email, password);
    }

    public Admin(){};

}
