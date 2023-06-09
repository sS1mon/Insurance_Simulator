package com.project.insurance.controller;

import com.project.insurance.model.dto.ClientRegDto;
import com.project.insurance.model.dto.InsureCarDto;
import com.project.insurance.model.dto.LoginDto;
import com.project.insurance.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/registration")
    public ResponseEntity<Object> register (@Valid @RequestBody ClientRegDto clientRegDto){
        try {
            return new ResponseEntity<>(clientService.clientRegistration(clientRegDto), HttpStatus.ACCEPTED);
        } catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("status", "400");
            String err = e.getMessage();
            if (e.getMessage().contains("messageTemplate=")){
                String [] msg = e.getMessage().split("messageTemplate=");
                 err = msg[msg.length-1];
                 msg = err.split("'");
                 err = msg[1];
            }
            response.put("error", err);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto){
        try {
            return  new ResponseEntity<>(clientService.loginToken(loginDto), HttpStatus.ACCEPTED);
        } catch (Exception e){
        Map<String, String> response = new HashMap<>();
        response.put("status", "400");
        String err = e.getMessage();
        if (e.getMessage().contains("messageTemplate=")){
            String [] msg = e.getMessage().split("messageTemplate=");
            err = msg[msg.length-1];
            msg = err.split("'");
            err = msg[1];
        }
        response.put("error", err);
        return ResponseEntity.badRequest().body(response);
        }
    }


    @PostMapping("/car/insure")
    public ResponseEntity<Object> insureCar(@Valid @RequestBody InsureCarDto insureCarDto){
        try {
            return new ResponseEntity<>(clientService.carInsuranceRegistration(insureCarDto), HttpStatus.ACCEPTED);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("status", "400");
            String err = e.getMessage();
            if (e.getMessage().contains("messageTemplate=")){
                String [] msg = e.getMessage().split("messageTemplate=");
                err = msg[msg.length-1];
                msg = err.split("'");
                err = msg[1];
            }
            response.put("error", err);
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
