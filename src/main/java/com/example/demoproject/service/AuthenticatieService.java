package com.example.demoproject.service;

import com.example.demoproject.domain.Gebruiker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Optional;

public interface AuthenticatieService extends UserDetailsService {
    public Gebruiker vindGebruiker(String naam);
    public Collection<Gebruiker> krijgAlleGebruikers();
    //public boolean login(Gebruiker gebruiker);

    UserDetails loadUserByUsername(String username);
    Gebruiker save(Gebruiker gebruiker);
}
