package com.project.insurance.controller;

import com.project.insurance.model.dto.ClientRegDto;
import com.project.insurance.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/registration")
    public ResponseEntity<Object> register (@RequestBody ClientRegDto clientRegDto){
        try {
            return new ResponseEntity<>(clientService.clientRegistration(clientRegDto), HttpStatus.ACCEPTED);
        } catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("status", "400");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
