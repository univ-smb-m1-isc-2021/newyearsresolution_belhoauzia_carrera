package com.example.newyear.persistence;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Initializer {

    private final ResolutionRepository repository;
    private final UserRepository userRepository;

    public Initializer(ResolutionRepository repository,UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initial(){
        if (repository.findAll().isEmpty()) {
            repository.saveAndFlush(new Resolution("Test 1","c'est un test d'affichage",1,1));
            repository.saveAndFlush(new Resolution("Test 2","c'est un deuxiem test",2,7));
            userRepository.saveAndFlush(new UserClass( "Zohir", UserClass.encrytePassword("oups"),false));
        }
    }
}
