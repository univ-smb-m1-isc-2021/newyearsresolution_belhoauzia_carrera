package com.example.newyear.service;

import com.example.newyear.persistence.*;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Resolution> myResolution(String username){
        UserClass u = userRepository.findByUsername(username);
        List<UserRes> ur = userResRepository.findByUser(u);
        List<Resolution> res = new ArrayList<>();
        for(int i = 0 ; i < ur.size() ; i++){
            Long id = ur.get(i).getResId();
            Optional<Resolution> re = repository.findById(id);
            Resolution r=null;
            try{
                r = re.get();
            }catch(NoSuchElementException e){
                System.out.println(e);
            }
            if(r != null){
                res.add(r);
            }
        }
        return res;
    }

    public boolean haveResolution(String username,int id){
        UserClass u = userRepository.findByUsername(username);
        Resolution r = repository.findById(id);
        UserRes res = userResRepository.findByResolution(r);
        if(res != null){
            return res.getUser().getId() == u.getId();
        }
        return false;
    }

    public void addUser(String username,String pass,Boolean isSocial){
        userRepository.saveAndFlush(new UserClass(username,pass,isSocial));
    }

    public void saveUser(UserClass u){
        userRepository.saveAndFlush(u);
    }

    public void removeUser(UserClass u){userRepository.delete(u);}
}
