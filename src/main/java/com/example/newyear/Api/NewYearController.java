package com.example.newyear.Api;

import com.example.newyear.persistence.Resolution;
import com.example.newyear.persistence.UserClass;
import com.example.newyear.service.ResolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public List<Resolution> title(){
        logger.info("Service Resolutions");
        return resolutionService.resolutionList().stream().collect(toList());

    }

    @GetMapping(value = "/api/myResolution")
    public List<Resolution> myResolution(@RequestParam String username){
        List<Resolution> l = resolutionService.myResolution(username);
        if(l.size()>0) {
            return l.stream().collect(toList());
        }else{
            return new ArrayList<>();
        }
    }

    @GetMapping(value = "/api/addResolutionToUser")
    @ResponseBody
    public String addResolutionToUser(@RequestParam String username,@RequestParam int id){
        if(resolutionService.haveResolution(username,id)){
            return "You already have this resolution";
        }else {
            resolutionService.addResolutionToUser(id,username);
            return "Resolution added succesfully";
        }
    }

    @GetMapping(value = "/api/newResolution")
    @ResponseBody
    public String newResolution(@RequestParam String title,@RequestParam String des,@RequestParam int nb_oc,@RequestParam int freq,@RequestParam String username){

        if(title != "" && des != "" && nb_oc > 0 && freq > 0) {
            resolutionService.addResolution(title, des, nb_oc, freq,username);
            logger.info("nouvelle resolution ajouté");
            return "ok";
        }else{logger.info("erreur nouvelle resolution");return "nok";}
    }
    @GetMapping(value = "/api/newUser")
    @ResponseBody
    public List<String> newUser(@RequestParam String username,@RequestParam String password,@RequestParam Boolean remember){
        logger.info("nouvelle utilisateur ajouté");
        List<String> res = new ArrayList<>();
        List<UserClass> ul = resolutionService.userList();
        for (int i = 0 ; i < ul.size();i++){
            if(ul.get(i).getUsername().equals(username)){
                res.add("Un utilisateur porte déjà ce nom");
                return res;
            }
        }
        resolutionService.addUser(username, UserClass.encrytePassword(password),false);
        res.add(username);
        return rememberMe(username, remember, res);
    }
    @GetMapping(value = "/api/login")
    public List<String> login(@RequestParam String username,@RequestParam String password,@RequestParam Boolean remember){
        List<UserClass> ul = resolutionService.userList();
        List<String> res = new ArrayList<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (int i = 0 ; i < ul.size();i++){
            if(ul.get(i).getUsername().equals(username)){
                if(encoder.matches(password, ul.get(i).getPassword())) {
                    logger.info("login success");
                    res.add(ul.get(i).getUsername());
                    return rememberMe(username, remember, res);
                }else{
                    break;
                }
            }
        }
        logger.info("login failed");
        res.add("null");
        return res;

    }
    @GetMapping(value = "/api/getToken")
    public String generateToken(@RequestParam String username){
        List<UserClass> ul = resolutionService.userList();
        for (int i = 0 ; i < ul.size();i++){
            if(ul.get(i).getUsername().equals(username)){
                ul.get(i).setToken();
                resolutionService.saveUser(ul.get(i));
                logger.info("token generated");
                return ul.get(i).getToken();
            }
        }
        logger.info("error during generation");
        return null;
    }
    @GetMapping(value = "/api/deleteAccount")
    public String deleteAccount(@RequestParam String username,@RequestParam String password){
        List<UserClass> ul = resolutionService.userList();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (int i = 0 ; i < ul.size();i++){
            if(ul.get(i).getUsername().equals(username)){
                if(encoder.matches(password, ul.get(i).getPassword())) {
                    resolutionService.removeUser(ul.get(i));
                    logger.info("account deleted");
                    return "Account deleted";
                }else{
                    return "Password incorrect";
                }
            }
        }
        logger.info("error account deletion");
        return null;
    }
    @GetMapping(value = "/api/changePassword")
    public String changePassword(@RequestParam String username,@RequestParam String password){
        List<UserClass> ul = resolutionService.userList();
        for (int i = 0 ; i < ul.size();i++){
            if(ul.get(i).getUsername().equals(username)){
                ul.get(i).setPassword(UserClass.encrytePassword(password));
                resolutionService.saveUser(ul.get(i));
                logger.info("password changed");
                return "Password have been changed";
            }
        }
        logger.info("error while changing password");
        return "Error while changing the password";
    }
    @GetMapping(value = "/api/auto_connect")
    public String autoConnection(@RequestParam String token){
        List<UserClass> ul = resolutionService.userList();
        for (int i = 0 ; i < ul.size();i++){
            if(token.equals(ul.get(i).getToken())){
                return ul.get(i).getUsername();
            }
        }
        logger.info("auto connect failed");
        return "null";

    }
    private List<String> rememberMe( String username, Boolean remember, List<String> res) {
        List<UserClass> ul;
        if(remember){
            UserClass u = null;
            ul = resolutionService.userList();
            for (int j = 0 ; j < ul.size();j++){
                if(ul.get(j).getUsername().equals(username)){
                    u = ul.get(j);
                }
            }
            u.setToken();
            res.add(u.getToken());
            resolutionService.saveUser(u);
        }
        return res;
    }
}
