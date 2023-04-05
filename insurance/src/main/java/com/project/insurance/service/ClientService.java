package com.project.insurance.service;

import com.project.insurance.model.dto.ClientRegDto;
import com.project.insurance.model.dto.ClientRegRespDto;

public interface ClientService {
    public ClientRegRespDto clientRegistration(ClientRegDto clientRegDto);
}
