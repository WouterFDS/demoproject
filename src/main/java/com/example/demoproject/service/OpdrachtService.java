package com.example.demoproject.service;

import com.example.demoproject.domain.Opdracht;

import java.util.Collection;

public interface OpdrachtService {

    public Collection<Opdracht> krijgAlleOpdrachten();
    public Opdracht vindOpdracht(int id);
    public void maakOpdracht(String naam, int belangrijkheid);
    public void updateOpdracht(int id, String naam, int belangrijkheid);
    public void verwijderOpdracht(int id);
}
