package com.project.insurance.service;

import com.project.insurance.enums.Type;
import com.project.insurance.model.dto.ClientRegDto;
import com.project.insurance.model.dto.ClientResponseDto;
import com.project.insurance.model.dto.InsureCarDto;
import com.project.insurance.model.entity.Client;
import com.project.insurance.repository.CarInsuranceRepository;
import com.project.insurance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CarInsuranceRepository carInsuranceRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CarInsuranceRepository carInsuranceRepository) {
        this.clientRepository = clientRepository;
        this.carInsuranceRepository = carInsuranceRepository;
    }

    public ClientResponseDto clientRegistration(ClientRegDto clientRegDto) {
        Client client = clientRepository.findByEmail(clientRegDto.getEmail());
        if (client == null) {
            client = new Client(clientRegDto);
            clientRepository.save(client);
            String txt = "Dear " + client.getName() + " " + client.getSureName() + " thanks for your trust, we will keep you updated";
                    return new ClientResponseDto(txt);
        } else {
            throw new IllegalStateException("Email is already registered!");
        }
    }

    public ClientResponseDto carInsuranceRegistration(InsureCarDto car){
        Client client = clientRepository.findByEmail(car.getEmail());
        if (carInsuranceRepository.findByVin(car.getVin()) != null){

        }else {
            throw new IllegalStateException("This car is already insured");
        }
        return new ClientResponseDto("thanks");
    }
}