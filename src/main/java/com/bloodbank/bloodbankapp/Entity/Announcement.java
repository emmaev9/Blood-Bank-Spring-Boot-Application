package com.bloodbank.bloodbankapp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
