package com.project.insurance.service;

import com.project.insurance.model.dto.ClientRegDto;
import com.project.insurance.model.dto.ClientResponseDto;

public interface ClientService {
    public ClientResponseDto clientRegistration(ClientRegDto clientRegDto);
}
