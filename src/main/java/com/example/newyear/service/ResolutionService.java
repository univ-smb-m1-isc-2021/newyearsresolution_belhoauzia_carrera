package com.example.newyear.service;

import com.example.newyear.persistence.Resolution;
import com.example.newyear.persistence.ResolutionRepository;
import com.example.newyear.persistence.UserClass;
import com.example.newyear.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResolutionService {

    private final ResolutionRepository repository;
    private final UserRepository userRepository;

    public ResolutionService(ResolutionRepository repository,UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Resolution> resolutionList(){
        return repository.findAll();
    }
    public List<UserClass> userList(){
        return userRepository.findAll();
    }

    public void addResolution(String title,String des,int nb_oc,int freq){
        repository.saveAndFlush(new Resolution(title,des,nb_oc,freq));
    }

    public void addUser(String username,String pass,Boolean isSocial){
        userRepository.saveAndFlush(new UserClass(username,pass,isSocial));
    }

    public void saveUser(UserClass u){
        userRepository.saveAndFlush(u);
    }
}
