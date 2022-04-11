package com.example.newyear.persistence;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class Initializer {

    private final ResolutionRepository repository;
    private final UserRepository userRepository;
    private final UserResRepository userResRepository;

    public Initializer(ResolutionRepository repository, UserRepository userRepository, UserResRepository userResRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.userResRepository = userResRepository;
    }

    @PostConstruct
    public void initial(){
        userResRepository.deleteAllInBatch();
        repository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        if (repository.findAll().isEmpty() && userRepository.findAll().isEmpty() &&  userResRepository.findAll().isEmpty()) {
            //creation d'uilisateur
            UserClass zohir = new UserClass( "Zohir", UserClass.encrytePassword("supermotdepasse"),false);
            userRepository.saveAndFlush(zohir);
            userRepository.saveAndFlush(new UserClass( "Haris", UserClass.encrytePassword("barbac"),false));
            userRepository.saveAndFlush(new UserClass( "Thomas", UserClass.encrytePassword("supermotdepasse"),false));

            //creation de resolution
            Resolution vegetable = new Resolution("Vegetables","Eat vegetables at each meal (twice a day)",2,1);
            repository.saveAndFlush(new Resolution("Bike","Do bike twice a week",2,7));
            repository.saveAndFlush(vegetable);

            //create userRes
            UserRes zohir_vege = new UserRes(zohir, vegetable, new Date("04/01/2022"));
            zohir_vege.addListe(new ResolutionDo(new Date("04/01/2022"),3));
            zohir_vege.addListe(new ResolutionDo(new Date("04/02/2022"),1));
            zohir_vege.addListe(new ResolutionDo(new Date("04/03/2022"),2));
            zohir_vege.addListe(new ResolutionDo(new Date("04/04/2022"),2));
            zohir_vege.addListe(new ResolutionDo(new Date("04/05/2022"),1));
            zohir_vege.addListe(new ResolutionDo(new Date("04/07/2022"),2));
            zohir_vege.addListe(new ResolutionDo(new Date("04/08/2022"),2));
            zohir_vege.addListe(new ResolutionDo(new Date("04/09/2022"),1));
            userResRepository.saveAndFlush(zohir_vege);
        }
    }
}
