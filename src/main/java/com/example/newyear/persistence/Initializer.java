package com.example.newyear.persistence;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            UserClass haris = new UserClass( "Haris", UserClass.encrytePassword("barbac"),false);
            UserClass thomas = new UserClass( "Thomas", UserClass.encrytePassword("supermotdepasse"),false);
            userRepository.saveAndFlush(zohir);
            userRepository.saveAndFlush(haris);
            userRepository.saveAndFlush(thomas);

            //creation de resolution
            Resolution vegetable = new Resolution("Vegetables","Eat vegetables at each meal (twice a day)",2,1);
            Resolution bike = new Resolution("Bike","Do bike twice a week",2,7);
            Resolution sleep = new Resolution("Sleep","Sleep before midnight",2,7);
            repository.saveAndFlush(bike);
            repository.saveAndFlush(sleep);
            repository.saveAndFlush(new Resolution("Mom","Call my mom",1,31));
            repository.saveAndFlush(new Resolution("Alcohol","Don't drink alcohol today",4,7));
            repository.saveAndFlush(new Resolution("Brush Teeth","Brush your teeth",3,1));
            repository.saveAndFlush(new Resolution("Wake up","Wake up before 10 am",1,1));
            repository.saveAndFlush(vegetable);

            //create userRes
            UserRes zohirvege = new UserRes(zohir, vegetable, new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2022"));
            zohirvege.addListe(new ResolutionDo("01/04/2022",3));
            zohirvege.addListe(new ResolutionDo("02/04/2022",1));
            zohirvege.addListe(new ResolutionDo("03/04/2022",2));
            zohirvege.addListe(new ResolutionDo("04/04/2022",2));
            zohirvege.addListe(new ResolutionDo("05/04/2022",1));
            zohirvege.addListe(new ResolutionDo("06/04/2022",2));
            zohirvege.addListe(new ResolutionDo("08/04/2022",2));
            zohirvege.addListe(new ResolutionDo("09/04/2022",1));
            userResRepository.saveAndFlush(zohirvege);

            userResRepository.saveAndFlush( new UserRes(haris, vegetable, new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2022")));
            userResRepository.saveAndFlush( new UserRes(thomas, vegetable, new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2022")));
            userResRepository.saveAndFlush( new UserRes(haris, bike, new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2022")));
            userResRepository.saveAndFlush( new UserRes(thomas, bike, new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2022")));
            userResRepository.saveAndFlush( new UserRes(haris, sleep, new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2022")));
        }
    }
}
