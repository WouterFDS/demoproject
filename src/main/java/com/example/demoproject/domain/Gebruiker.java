package com.example.demoproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public")
public class Gebruiker {
    @Id
    private String naam;
    @Column
    private String wachtwoord;
    @Column
    private boolean isAdmin;

    public Gebruiker(String naam, String wachtwoord, boolean isAdmin) {
        setNaam(naam);
        setWachtwoord(wachtwoord);
        setAdmin(isAdmin);
    }

    public Gebruiker(){

    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object O){
        System.out.println("equals");
        return true;
    }
}
