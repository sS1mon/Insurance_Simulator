package com.project.insurance.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "You need to fill out email in order to login!")
    private String email;
    @NotBlank(message = "You need to fill out your password in order to login!")
    private String password;
}
