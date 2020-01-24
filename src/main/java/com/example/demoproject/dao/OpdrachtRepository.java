package com.example.demoproject.dao;

import com.example.demoproject.domain.Opdracht;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OpdrachtRepository extends CrudRepository<Opdracht, String> {

    Opdracht findById(int id);
    void deleteById(int id);
}
