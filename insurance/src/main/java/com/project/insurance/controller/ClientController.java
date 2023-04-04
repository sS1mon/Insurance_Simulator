package com.project.insurance.controller;

import com.project.insurance.model.dto.ClientRegDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @PostMapping("/registration")
    public ResponseEntity<Object> register (@RequestBody ClientRegDto clientRegDto){

    }
}
