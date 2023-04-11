package com.project.insurance.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ClientRegDto {
    @NotNull
    @NotBlank(message = "Please fill out your name!")
    private String name;
    @NotNull
    @NotBlank(message = "Please fill out your sure name!")
    private String surName;
    @NotNull
    @NotBlank(message = "Please fill out your email!")
    private String email;
    private Long phoneNumber;
    @NotNull
    @NotBlank(message = "Please fill out your day of birth!")

    private String dayOfBirth;
}
