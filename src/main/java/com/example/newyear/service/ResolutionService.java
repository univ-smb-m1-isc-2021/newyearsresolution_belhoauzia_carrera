package com.example.newyear.service;

import com.example.newyear.persistence.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResolutionService {

    private final ResolutionRepository repository;
    private final UserRepository userRepository;
    private final UserResRepository userResRepository;

    public ResolutionService(ResolutionRepository repository, UserRepository userRepository, UserResRepository userResRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.userResRepository = userResRepository;
    }

    public List<Resolution> resolutionList(){
        return repository.findAll();
    }
    public List<UserClass> userList(){
        return userRepository.findAll();
    }

    public void addResolution(String title,String des,int nb_oc,int freq,String username){
        Resolution r = new Resolution(title,des,nb_oc,freq);
        UserClass u = userRepository.findByUsername(username);
        repository.saveAndFlush(r);
        userResRepository.saveAndFlush(new UserRes(u, r, new Date(System.currentTimeMillis()),0));
    }

    public void addResolutionToUser(int id,String username){
        Resolution r = repository.findById(id);
        UserClass u = userRepository.findByUsername(username);
        userResRepository.saveAndFlush(new UserRes(u, r, new Date(System.currentTimeMillis()),0));
    }

    public void addUser(String username,String pass,Boolean isSocial){
        userRepository.saveAndFlush(new UserClass(username,pass,isSocial));
    }

    public void saveUser(UserClass u){
        userRepository.saveAndFlush(u);
    }

    public void removeUser(UserClass u){userRepository.delete(u);}
}
