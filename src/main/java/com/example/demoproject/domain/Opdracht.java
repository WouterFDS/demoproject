package com.example.demoproject.domain;


import javax.persistence.*;

@Entity
@Table(schema="public")
public class Opdracht {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String naam;
    @Column
    private int belangrijkheid;

    public Opdracht(int id,String naam, int belangrijkheid) {
        setId(id);
        setNaam(naam);
        setBelangrijkheid(belangrijkheid);
    }

    public Opdracht(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getBelangrijkheid() {
        return belangrijkheid;
    }

    public void setBelangrijkheid(int belangrijkheid) {
        this.belangrijkheid = belangrijkheid;
    }

}
