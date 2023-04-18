package com.project.insurance.service;

import com.project.insurance.model.dto.ClientRegDto;
import com.project.insurance.model.dto.ClientResponseDto;
import com.project.insurance.model.dto.InsureCarDto;
import com.project.insurance.model.dto.LoginDto;
import com.project.insurance.model.entity.CarInsurance;
import com.project.insurance.model.entity.Client;
import com.project.insurance.model.entity.Roles;
import com.project.insurance.repository.CarInsuranceRepository;
import com.project.insurance.repository.ClientRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CarInsuranceRepository carInsuranceRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CarInsuranceRepository carInsuranceRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.clientRepository = clientRepository;
        this.carInsuranceRepository = carInsuranceRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ClientResponseDto clientRegistration(ClientRegDto clientRegDto) {
        Client client = clientRepository.findByEmail(clientRegDto.getEmail());
        if (client == null) {
            if (clientRegDto.getPassword().matches(clientRegDto.getRepeatPassword())) {
                if (carInsuranceRepository.findAll().size() == 0) {
                    client = new Client(clientRegDto, Roles.ADMIN);

                } else {
                    client = new Client(clientRegDto, Roles.CLIENT);
                }
                client.setPassword(passwordEncoder.encode(clientRegDto.getPassword()));
                clientRepository.save(client);
                String txt = "Dear " + client.getName() + " " + client.getSureName() + " thanks for your trust, we will keep you updated.";
                return new ClientResponseDto(txt);
            }
            else {
                throw new BadCredentialsException("Passwords aren't matching!");
            }
        } else {
            throw new IllegalStateException("Email is already registered!");
        }
    }

    @Override
    public String loginToken(LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        return tokenGenerator(authentication);

    }

    private String tokenGenerator(Authentication aut){
        if (aut != null){
            SecretKey key = Keys.hmacShaKeyFor(System.getenv("JWT").getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("InsuranceSimulator").setSubject("JWT token")
                    .claim("username", aut.getName())
                    .claim("id", clientRepository.findByEmail(aut.getName()).getId())
                    .claim("authorities", getAuth(aut.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(ZonedDateTime.now().plusDays(5).toInstant()))
                    .signWith(key).compact();
            return jwt;
        } else {
            throw new RuntimeException("Something went wrong wit Token Generation! Try again!");
        }
    }

    private String getAuth(Collection<? extends GrantedAuthority> authorities){
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : authorities){
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

    @Override
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