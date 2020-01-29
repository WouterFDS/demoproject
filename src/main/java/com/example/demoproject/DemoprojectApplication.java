package com.example.demoproject;


import com.example.demoproject.domain.Opdracht;
import com.example.demoproject.service.impl.Facade;
//import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Collection;

@SpringBootApplication
@PropertySource("file:/home/wouter/IdeaProjects/demoproject/config/local/application2.properties")
public class DemoprojectApplication {

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DemoprojectApplication.class, args);
        //Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/projectDB","Gebruiker","wachtwoord").load();
        //flyway.migrate();
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
