package com.project.insurance.service;

import com.project.insurance.model.dto.AdminDto;
import com.project.insurance.model.dto.ListOfClientsDto;
import com.project.insurance.model.entity.Client;
import com.project.insurance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private final ClientRepository clientRepository;

    @Autowired
    public AdminServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ListOfClientsDto seeAllClients(AdminDto admin){
        String pw = System.getenv("ADMIN");
        if (!pw.equals(admin.getPassword())){
            throw new IllegalStateException("Wrong password!");
        }
        List<Client> clientList = clientRepository.findAll();
       if (clientList.isEmpty()){
           throw new IllegalStateException("There are no client's to show");
       }
       return new ListOfClientsDto(clientList);
    }

}
