package com.example.demoproject.dao;

import com.example.demoproject.domain.Gebruiker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface GebruikerRepository extends CrudRepository<Gebruiker, String>{

}
