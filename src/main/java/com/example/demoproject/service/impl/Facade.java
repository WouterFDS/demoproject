package com.example.demoproject.service.impl;

import com.example.demoproject.dao.GebruikerRepository;
import com.example.demoproject.dao.OpdrachtRepository;
import com.example.demoproject.domain.Gebruiker;
import com.example.demoproject.domain.Opdracht;
import com.example.demoproject.service.AuthenticatieService;
import com.example.demoproject.service.OpdrachtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service(value="facade")
public class Facade implements AuthenticatieService, OpdrachtService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private OpdrachtRepository opdrachtRepository;

    //@Autowired
    //private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public Gebruiker vindGebruiker(String naam) {
        if(naam==null){
            throw new NullPointerException();
        }
        Optional<Gebruiker> gebruikerOptional = gebruikerRepository.findById(naam);
        Gebruiker gebruiker = null;
        if(gebruikerOptional.isPresent()){
            gebruiker = gebruikerOptional.get();
            gebruiker.setNaam(naam);
            gebruiker.setWachtwoord(gebruiker.getWachtwoord());
        }

        return gebruiker;
    }

    @Override
    public Gebruiker save(Gebruiker user) {
        System.out.println("inSave");
        Gebruiker newUser = new Gebruiker();
        newUser.setNaam(user.getNaam());
        newUser.setWachtwoord(user.getWachtwoord());
        newUser.setAdmin(user.isAdmin());
        return gebruikerRepository.save(newUser);
    }



    @Override
    public Collection<Gebruiker> krijgAlleGebruikers() {
        ArrayList<Gebruiker> gebruikers = new ArrayList<Gebruiker>();
        gebruikerRepository.findAll().forEach(gebruikers::add);
        return gebruikers;
    }


    /*
    @Override
    public boolean login(Gebruiker gebruiker) {
        if(gebruiker==null){
            throw new NullPointerException();
        }
        boolean juisteGegevens = false;
        Gebruiker gIndata = vindGebruiker(gebruiker.getNaam());
        if(gIndata!=null){
            gebruiker.setWachtwoord(bcryptEncoder.encode(gebruiker.getWachtwoord()));
            juisteGegevens = gIndata.getWachtwoord().equals(gebruiker.getWachtwoord());
        }
        return juisteGegevens;
    }*/



    @Override
    public UserDetails loadUserByUsername(String gebruikersNaam) {
        Gebruiker gebruiker = vindGebruiker(gebruikersNaam);
        List rechten = null;
        if(gebruiker.isAdmin()){
            rechten = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            rechten = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new User(gebruiker.getNaam(),gebruiker.getWachtwoord(),rechten);
    }
    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Collection<Opdracht> krijgAlleOpdrachten() {
        ArrayList<Opdracht> opdrachten = new ArrayList<Opdracht>();
        opdrachtRepository.findAll().forEach(opdrachten::add);
        return opdrachten;
    }

    @Override
    public Opdracht vindOpdracht(int id) {
        Opdracht opdracht = opdrachtRepository.findById(id);

        return opdracht;
    }

    @Override
    public void maakOpdracht(String naam, int belangrijkheid) {
        if(naam==null){
            throw new NullPointerException();
        }
        Opdracht teMaken = new Opdracht(0,naam,belangrijkheid);
        opdrachtRepository.save(teMaken);
    }

    @Override
    public void updateOpdracht(int id, String naam, int belangrijkheid) {
        if(naam==null){
            throw new NullPointerException();
        }
        if(belangrijkheid<0||id<0){
            throw new IllegalArgumentException();
        }
        Opdracht updateVersie = new Opdracht(id,naam,belangrijkheid);
        opdrachtRepository.save(updateVersie);
    }

    @Override
    public void verwijderOpdracht(int id) {
        if(id<0){
            throw new IllegalArgumentException();
        }
        opdrachtRepository.deleteById(id);
    }

}
