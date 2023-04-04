package com.project.insurance.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegDto {
    private String name;
    private String surName;
    private String email;
    private String phoneNumber;
    private String dayOfBirth;
}
