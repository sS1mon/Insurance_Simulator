package com.project.insurance.config;

import com.project.insurance.model.entity.Client;
import com.project.insurance.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

public class JwtAuth {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public JwtAuth(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Authentication authentication(Authentication auth) throws AuthenticationException{
        String email = auth.getName();
        String password = auth.getCredentials().toString();
        Client client = clientRepository.findByEmail(email);
        if (client != null){
            if (passwordEncoder.matches(password, client.getPassword())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(client.getRole().toString()));
                return new UsernamePasswordAuthenticationToken(email, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        } else {
            throw new BadCredentialsException("There is no such user");
        }
    }

    public boolean allows(Class<?> authentication){
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}