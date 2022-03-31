package com.example.newyear.persistence;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Initializer {

    private final ResolutionRepository repository;

    public Initializer(ResolutionRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void initial(){
        if (repository.findAll().isEmpty()) {
            repository.saveAndFlush(new Resolution("Test 1","c'est un test d'affichage",1,1));
            repository.saveAndFlush(new Resolution("Test 2","c'est un deuxiem test",2,7));}
    }
}
