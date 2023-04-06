package com.project.insurance.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegDto {
    @NotBlank(message = "Please fill out your name!")
    private String name;
    @NotBlank(message = "Please fill out your sure name!")
    private String surName;
    @NotBlank(message = "Please fill out your email!")
    private String email;
    private Long phoneNumber;
    @NotBlank(message = "Please fill out your day of birth!")
    private String dayOfBirth;
}
