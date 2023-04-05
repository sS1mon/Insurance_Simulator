package com.project.insurance.service;

import com.project.insurance.model.dto.ClientRegDto;
import com.project.insurance.model.dto.ClientRegRespDto;
import com.project.insurance.model.entity.Client;
import com.project.insurance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientRegRespDto clientRegistration(ClientRegDto clientRegDto) {
        if (clientRegDto == null || clientRegDto.getName() == null || clientRegDto.getName().isEmpty() ||
                clientRegDto.getSurName() == null || clientRegDto.getSurName().isEmpty() ||
                clientRegDto.getEmail() == null || clientRegDto.getEmail().isEmpty() ||
                clientRegDto.getDayOfBirth() == null || clientRegDto.getDayOfBirth().isEmpty()){

            String text = "Please fill out following missing fields:";
            if (clientRegDto.getName() == null || clientRegDto.getName().isEmpty()) {
                text += " name;";
            }
            if (clientRegDto.getSurName() == null || clientRegDto.getSurName().isEmpty()) {
                text += " sure name;";
            }
            if (clientRegDto.getEmail() == null || clientRegDto.getEmail().isEmpty()) {
                text += " email;";
            }
            if (clientRegDto.getDayOfBirth() == null || clientRegDto.getDayOfBirth().isEmpty()) {
                text += " day of birth;";
            }
            throw new IllegalStateException(text);
        }
        Client client = clientRepository.findByEmail(clientRegDto.getEmail());
        if (client == null) {
            client = new Client(clientRegDto.getName(), clientRegDto.getSurName(), clientRegDto.getDayOfBirth(), clientRegDto.getEmail(),
                    clientRegDto.getPhoneNumber());
            clientRepository.save(client);
            String txt = "Dear " + client.getName() + " " + client.getSureName() + " thanks for your trust, we will keep you updated";
                    return new ClientRegRespDto(txt);
        } else {
            throw new IllegalStateException("Email is already registered!");
        }
    }
}