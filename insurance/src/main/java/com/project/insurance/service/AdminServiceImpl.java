package com.project.insurance.service;

import com.project.insurance.model.dto.AdminDto;
import com.project.insurance.model.dto.ListOfClientsDto;
import com.project.insurance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    private final ClientRepository clientRepository;

    @Autowired
    public AdminServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ListOfClientsDto seeAllClients(AdminDto admin){
        if (admin.getPassword().equals(System.getenv("ADMIN_PW"))){
            throw new IllegalStateException("Wrong password!");
        }
        return new ListOfClientsDto(clientRepository.findAll());
    }

}
