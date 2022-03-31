package com.example.newyear.Api;

import com.example.newyear.persistence.Resolution;
import com.example.newyear.service.ResolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class NewYearController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ResolutionService resolutionService;

    public NewYearController(ResolutionService resolutionService) {
        this.resolutionService = resolutionService;
    }

    @GetMapping(value = "/api/AllResolutions")
    public List<String> title(){
        logger.info("Service Resolutions");
        return resolutionService.resolutionList().stream().map(p ->p.getTitle()).collect(toList());

    }

}
