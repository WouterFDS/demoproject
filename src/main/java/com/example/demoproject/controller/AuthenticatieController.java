package com.example.demoproject.controller;

import com.example.demoproject.domain.ApiResponse;
import com.example.demoproject.domain.AuthToken;
import com.example.demoproject.domain.Gebruiker;
import com.example.demoproject.service.AuthenticatieService;
import com.example.demoproject.config.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("/authenticator")
public class AuthenticatieController {

    @Autowired
    AuthenticatieService facade;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value="",method = RequestMethod.POST)
    public ApiResponse login(@RequestBody Gebruiker gebruiker) throws AuthenticationException{
            AuthToken token = null;
            String antwoord = "gebruiker niet gevonden in onze database";
            HttpStatus status = null;

                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                        gebruiker.getNaam(),
                        gebruiker.getWachtwoord()
                );
                final Authentication authentication = authenticationManager.authenticate(upat);
                if(authentication.isAuthenticated()){
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    final String tokenString = doGenerateToken(gebruiker.getNaam());
                    Gebruiker gebruikerIndata = facade.vindGebruiker(gebruiker.getNaam());
                    token = new AuthToken(tokenString,gebruikerIndata.isAdmin());
                    status = HttpStatus.OK;
                    antwoord= "gebruiker ingelogt";
                }
                else{
                    final String tokenString = doGenerateToken(gebruiker.getNaam());
                    token = new AuthToken(tokenString,false);
                    antwoord= "gebruikersgegevens fout";
                    status = HttpStatus.UNAUTHORIZED;
                }





            return new ApiResponse(status.value(),antwoord,token);

    }

    private String doGenerateToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("WouterT")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY)
                .compact();
    }


}

