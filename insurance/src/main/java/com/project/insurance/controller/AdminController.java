package com.project.insurance.controller;

import com.project.insurance.model.dto.AdminDto;
import com.project.insurance.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/list")
    public ResponseEntity<Object> getListOfClients(@Valid @RequestBody AdminDto admin){
        return new ResponseEntity<>(adminService.seeAllClients(admin), HttpStatus.ACCEPTED);
    }
}
