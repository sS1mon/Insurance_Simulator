package com.project.insurance.service;

import com.project.insurance.model.dto.ClientRegDto;
import com.project.insurance.model.dto.ClientResponseDto;
import com.project.insurance.model.dto.InsureCarDto;
import com.project.insurance.model.entity.CarInsurance;
import com.project.insurance.model.entity.Client;
import com.project.insurance.model.entity.Roles;
import com.project.insurance.repository.CarInsuranceRepository;
import com.project.insurance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CarInsuranceRepository carInsuranceRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CarInsuranceRepository carInsuranceRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.carInsuranceRepository = carInsuranceRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ClientResponseDto clientRegistration(ClientRegDto clientRegDto) {
        Client client = clientRepository.findByEmail(clientRegDto.getEmail());
        if (client == null) {
            if (clientRegDto.getPassword().matches(clientRegDto.getRepeatPassword())) {
                if (carInsuranceRepository.findAll().size() == 0) {
                    client = new Client(clientRegDto, Roles.ADMIN);

                } else {
                    client = new Client(clientRegDto, Roles.CLIENT);
                }
                clientRepository.save(client);
                String txt = "Dear " + client.getName() + " " + client.getSureName() + " thanks for your trust, we will keep you updated.";
                return new ClientResponseDto(txt);
            }
            else {
                throw new IllegalStateException("Passwords aren't matching!");
            }
        } else {
            throw new IllegalStateException("Email is already registered!");
        }
    }

    public ClientResponseDto carInsuranceRegistration(InsureCarDto car){
        Client client = clientRepository.findByEmail(car.getEmail());
        if (client == null){
            throw new IllegalStateException("You have to register to be our client first");
        }
        if (carInsuranceRepository.findByVin(car.getVin()) == null){
            CarInsurance carInsurance = new CarInsurance(car, client);
            carInsuranceRepository.save(carInsurance);
            return new ClientResponseDto("Your " + car.getBrand() + " " + car.getModel() + " has been insured.");
        }else {
            throw new IllegalStateException("This car is already insured");
        }
    }
}