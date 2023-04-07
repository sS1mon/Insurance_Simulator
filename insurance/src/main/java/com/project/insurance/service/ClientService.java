package com.project.insurance.service;

import com.project.insurance.model.dto.ClientRegDto;
import com.project.insurance.model.dto.ClientResponseDto;
import com.project.insurance.model.dto.InsureCarDto;

public interface ClientService {
    public ClientResponseDto clientRegistration(ClientRegDto clientRegDto);
    public ClientResponseDto carInsuranceRegistration(InsureCarDto car);
}
