package com.Myfirstshopping.website.Myfirstshopping.model;


import jakarta.persistence.*;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private UUID id;
    private String name;
    private int age;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable=false)
    private String password;
    @Column(unique = true, nullable = false)
    private Long phoneNumber;
    @Column(nullable=false)
    private String address;
    @Column(nullable=false)
    private String userType;




}
