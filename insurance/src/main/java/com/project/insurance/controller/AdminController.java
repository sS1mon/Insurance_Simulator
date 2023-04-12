package com.project.insurance.controller;

import com.project.insurance.model.dto.AdminDto;
import com.project.insurance.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/list-clients")
    public ResponseEntity<Object> getListOfClients(@Valid @RequestBody AdminDto admin){
        try {
            return new ResponseEntity<>(adminService.seeAllClients(admin), HttpStatus.ACCEPTED);
        } catch (Exception e){
        Map<String, String> response = new HashMap<>();
        response.put("status", "400");
        response.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/list-cars")
    public ResponseEntity<Object> getListOfCars(@Valid @RequestBody AdminDto admin){
        try {
            return new ResponseEntity<>(adminService.seeAllInsuredCars(admin), HttpStatus.ACCEPTED);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("status", "400");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
