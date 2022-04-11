package com.example.newyear.persistence;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
    public void initial() throws ParseException {
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
            UserRes zohir_vege = new UserRes(zohir, vegetable, new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2022"));
            zohir_vege.addListe(new ResolutionDo("01/04/2022",3));
            zohir_vege.addListe(new ResolutionDo("02/04/2022",1));
            zohir_vege.addListe(new ResolutionDo("03/04/2022",2));
            zohir_vege.addListe(new ResolutionDo("04/04/2022",2));
            zohir_vege.addListe(new ResolutionDo("05/04/2022",1));
            zohir_vege.addListe(new ResolutionDo("06/04/2022",2));
            zohir_vege.addListe(new ResolutionDo("08/04/2022",2));
            zohir_vege.addListe(new ResolutionDo("09/04/2022",1));
            userResRepository.saveAndFlush(zohir_vege);
        }
    }
}
