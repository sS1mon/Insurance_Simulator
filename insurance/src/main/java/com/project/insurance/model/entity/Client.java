package com.project.insurance.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "sure_name", nullable = false)
    private String sureName;
    @Column(name = "day_of_birh", nullable = false)
    private String dayOfBirth;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phone_number", unique = true)
    private Long phoneNumber;

    public Client(String name, String sureName, String dayOfBirth, String email, Long phoneNumber) {
        this.name = name;
        this.sureName = sureName;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
