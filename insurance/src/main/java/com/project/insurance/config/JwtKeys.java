package com.project.insurance.config;

import com.project.insurance.model.entity.Client;
import com.project.insurance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class JwtKeys {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JwtKeys(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authentication(Authentication auth) throws AuthenticationException{
        String email = auth.getName();
        String passw = auth.getCredentials().toString();
        Client client = clientRepository.findByEmail(email);
        if (client != null){
            if (passwordEncoder.matches(passw, client.getPassword())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(client.getRole))
            }
        }
    }
}