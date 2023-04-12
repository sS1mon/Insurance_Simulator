package com.project.insurance.service;

import com.project.insurance.model.dto.AdminDto;
import com.project.insurance.model.dto.InsuredCarDetailsDto;
import com.project.insurance.model.dto.InsuredCarListDto;
import com.project.insurance.model.dto.ListOfClientsDto;
import com.project.insurance.model.entity.CarInsurance;
import com.project.insurance.model.entity.Client;
import com.project.insurance.repository.CarInsuranceRepository;
import com.project.insurance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private final ClientRepository clientRepository;
    private final CarInsuranceRepository carInsuranceRepository;

    @Autowired
    public AdminServiceImpl(ClientRepository clientRepository, CarInsuranceRepository carInsuranceRepository) {
        this.clientRepository = clientRepository;
        this.carInsuranceRepository = carInsuranceRepository;
    }

    public ListOfClientsDto seeAllClients(AdminDto admin){
        checkForPassword(admin);
        List<Client> clientList = clientRepository.findAll();
       if (clientList.isEmpty()){
           throw new IllegalStateException("There are no client's to show");
       }
       return new ListOfClientsDto(clientList);
    }

    public InsuredCarListDto seeAllInsuredCars(AdminDto admin){
        checkForPassword(admin);
        List<CarInsurance> cars = carInsuranceRepository.findAll();
        List<InsuredCarDetailsDto> list = new ArrayList<>();
        for (CarInsurance car: cars) {
            list.add(new InsuredCarDetailsDto(car));
        }
        return new InsuredCarListDto(list);
    }

    private void checkForPassword(AdminDto admin){
        String pw = System.getenv("ADMIN");
        if (!pw.equals(admin.getPassword())){
            throw new IllegalStateException("Wrong password!");
        }
    }

}
