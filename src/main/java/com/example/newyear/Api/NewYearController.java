package com.example.newyear.Api;

import com.example.newyear.persistence.Resolution;
import com.example.newyear.persistence.UserClass;
import com.example.newyear.service.ResolutionService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
    public String newUser(@RequestParam String username,@RequestParam String password){
        logger.info("nouvelle utilisateur ajouté");
        List<UserClass> ul = resolutionService.userList();
        for (int i = 0 ; i < ul.size();i++){
            if(ul.get(i).getUsername().equals(username)){
                return "Un utilisateur porte déjà ce nom";
            }
        }
        resolutionService.addUser(username, UserClass.encrytePassword(password));
        return username;
    }
    @GetMapping(value = "/api/login")
    public String login(@RequestParam String username,@RequestParam String password){
        List<UserClass> ul = resolutionService.userList();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (int i = 0 ; i < ul.size();i++){
            if(ul.get(i).getUsername().equals(username)){
                if(encoder.matches(password, ul.get(i).getPassword())) {
                    logger.info("login success");
                    return ul.get(i).getUsername();
                }else{
                    break;
                }
            }
        }
        logger.info("login failed");
        return "null";

    }
}
