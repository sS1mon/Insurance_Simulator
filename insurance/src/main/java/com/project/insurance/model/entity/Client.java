package com.project.insurance.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.insurance.model.dto.ClientRegDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Please fill the name!")
    @Column(name = "name", nullable = false,length = 35)
    private String name;
    @NotNull(message = "Please fill the sure name!")
    @Column(name = "sure_name", nullable = false, length = 35)
    private String sureName;
    @Column(name = "day_of_birh", nullable = false)
    @Pattern(regexp = "^[0-9_.]{10}$", message = "Day of birth must be in this format: DD.MM.YYYY")
    private String dayOfBirth;
    @Column(name = "email", nullable = false, unique = true,length = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Please check if your email is correct!")
    private String email;
    @Column(name = "phone_number", unique = true)
    private Long phoneNumber;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CarInsurance> carInsurance;

    public Client(String name, String sureName, String dayOfBirth, String email, Long phoneNumber) {
        this.name = name;
        this.sureName = sureName;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Client (ClientRegDto reg){
        this.name = reg.getName();
        this.sureName = reg.getSurName();
        this.dayOfBirth = reg.getDayOfBirth();
        this.email = reg.getEmail();
        this.phoneNumber = reg.getPhoneNumber();
    }
}
