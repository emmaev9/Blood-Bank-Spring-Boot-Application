package com.bloodbank.bloodbankapp.Entity;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User uses;

    public Role(String name){
        this.name = name;
    }

    public Role(){}

    public User getUser() {
        return uses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
