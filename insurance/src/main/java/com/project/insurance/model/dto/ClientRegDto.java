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
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Please check if your email is correct!")
    private String email;
    private Long phoneNumber;
    @NotNull
    @NotBlank(message = "Please fill out your day of birth!")
    @Pattern(regexp = "^[0-9_.]{10}$", message = "Day of birth must be in this format: DD.MM.YYYY")
    private String dayOfBirth;
    @NotBlank(message = "Please crate a password for yourself!")
    //regex for minimum of characters
    @Pattern(regexp = "^.{5,}$")
    private String password;
    @NotBlank(message = "Retype your password!")
    @Pattern(regexp = "^.{5,}$")
    private String repeatPassword;
}
