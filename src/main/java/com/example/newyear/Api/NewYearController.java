package com.example.newyear.Api;

import com.example.newyear.persistence.Resolution;
import com.example.newyear.persistence.UserClass;
import com.example.newyear.service.ResolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping(value = "/api/newResolution")
    @ResponseBody
    public void newResolution(@RequestParam String title,@RequestParam String des,@RequestParam int nb_oc,@RequestParam int freq){
        logger.info("nouvelle resolution ajouté");
        resolutionService.addResolution(title,des,nb_oc,freq);
    }
    @GetMapping(value = "/api/newUser")
    @ResponseBody
    public void newUser(@RequestParam String username,@RequestParam String password){
        logger.info("nouvelle utilisateur ajouté");
        resolutionService.addUser(username, UserClass.encrytePassword(password));
    }

}
