package com.example.newyear.service;

import com.example.newyear.persistence.Resolution;
import com.example.newyear.persistence.ResolutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResolutionService {

    private final ResolutionRepository repository;

    public ResolutionService(ResolutionRepository repository) {
        this.repository = repository;
    }

    public List<Resolution> resolutionList(){
        return repository.findAll();
    }
}
