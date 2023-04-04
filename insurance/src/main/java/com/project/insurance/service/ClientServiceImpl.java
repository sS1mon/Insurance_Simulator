package com.project.insurance.service;

import com.project.insurance.model.dto.ClientRegDto;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl {

    public void clientRegistration(ClientRegDto clientRegDto) {
        if (clientRegDto == null){
            String text = "Please fill out following missing fields:";
            if (clientRegDto.getName() == null || clientRegDto.getSurName().isEmpty()){
                text += " name;";
            }
            if (clientRegDto.getSurName() == null || clientRegDto.getSurName().isEmpty()){
                text += " sure name;";
            }
            if (clientRegDto.getEmail() == null || clientRegDto.getEmail().isEmpty()){
                text += " email;";
            }
            if (clientRegDto.getPhoneNumber() == null || clientRegDto.getPhoneNumber().isEmpty()){
                text += " phone number;";
            }
            if (clientRegDto.getDayOfBirth() == null || clientRegDto.getDayOfBirth().isEmpty()){
                text += " day of birth;";
            }
            throw new IllegalStateException(text);
        }
        
    }
}
