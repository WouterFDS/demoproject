package com.example.demoproject.config;

import com.example.demoproject.domain.Gebruiker;
import com.example.demoproject.service.impl.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.ArrayList;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource(name = "facade")
    private Facade facade ;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new AuthenticationManager(){

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                Gebruiker gebruiker = facade.vindGebruiker(authentication.getName());
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        gebruiker.getNaam(),
                        gebruiker.getWachtwoord()
                );
                new StringBuilder(authentication.getName()).reverse().toString();

                if(gebruiker!=null&&gebruiker.getWachtwoord().equals(new StringBuilder(authentication.getCredentials().toString()).reverse().toString())){
                    auth = new UsernamePasswordAuthenticationToken(
                            gebruiker.getNaam(),
                            gebruiker.getWachtwoord(),
                            new ArrayList<GrantedAuthority>()
                    );
                }
                return auth;

            }
        };
    }




    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/opdracht/maak").hasRole("ADMIN")
                .antMatchers("/opdracht/verwijder").hasRole("ADMIN")
                .antMatchers("/authenticator/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }




}
