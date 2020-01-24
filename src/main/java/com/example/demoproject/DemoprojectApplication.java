package com.example.demoproject;


import com.example.demoproject.dao.GebruikerRepository;
import com.example.demoproject.domain.Opdracht;
import com.example.demoproject.service.impl.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;


@SpringBootApplication
public class DemoprojectApplication {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(DemoprojectApplication.class, args);

    }

    @Bean
    public CommandLineRunner init(Facade facade){
        return args -> {

            UserDetailsService fac = new Facade();
            Collection<Opdracht> opdrachten = facade.krijgAlleOpdrachten();
            for(Opdracht opdracht:opdrachten){
                System.out.println(opdracht.getNaam());
            }


        };
    }
}
