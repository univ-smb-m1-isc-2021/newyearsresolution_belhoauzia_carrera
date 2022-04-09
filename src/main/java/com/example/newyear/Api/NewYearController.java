package com.example.newyear.Api;

import com.example.newyear.persistence.*;
import com.example.newyear.service.ResolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.stream.Collectors.toList;

@RestController
public class NewYearController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ResolutionService resolutionService;

    public NewYearController(ResolutionService resolutionService) {
        this.resolutionService = resolutionService;
    }

    @GetMapping(value = "/api/AllResolutions")
    public List<ResolutionHome> title(){
        logger.info("Service Resolutions");
        List<Resolution> l = resolutionService.resolutionList().stream().collect(toList());
        ArrayList<PopularityResolution> li = new ArrayList<PopularityResolution>();
        ArrayList<ResolutionHome> res = new ArrayList<>();
        for(int i = 0 ; i < l.size(); i++){
            li.add(new PopularityResolution(l.get(i),resolutionService.nbUserResResolution(l.get(i))));
        }
        Collections.sort(li, new Comparator<PopularityResolution>(){
            public int compare(PopularityResolution o1, PopularityResolution o2){
                return (int) (o2.getPopularity() - o1.getPopularity());
            }
        });
        for(int i = 0 ; i < 3 ; i++){
            if(li.size() > i){
                res.add(new ResolutionHome(li.get(i).getR(),10));
            }
        }
        int random_left = 2;
        boolean fini = false;
        ArrayList<ResolutionHome> done = new ArrayList<>();
        while(random_left > 0 && !fini){
            int random = new Random().nextInt(l.size()) ;
            if(!isInArray(res,l.get(random))){
                res.add(new ResolutionHome(l.get(random),10));
                random_left--;
            }
            if(!isInArray(done,l.get(random))){
                done.add(new ResolutionHome( l.get(random),10));
            }
            fini = isDone(done,l);
        }
        return res;

    }
    public boolean isDone(ArrayList<ResolutionHome> l ,List<Resolution> d){
        for(int i = 0 ; i < d.size(); i++){
            if(!isInArray(l,d.get(i))){
                return false;
            }
        }
        return true;
    }
    public boolean isInArray(ArrayList<ResolutionHome> l , Resolution r){
        for(int i = 0; i < l.size();i++){
            if(l.get(i).getResolution().getId() == r.getId()){
                return true;
            }
        }
        return false;
    }

    @GetMapping(value = "/api/github")
    public ArrayList<Integer> github(@RequestParam String username){
        UserClass u = resolutionService.getUser(username);
        Date today = new Date(System.currentTimeMillis());
        ArrayList<UserRes> url = (ArrayList<UserRes>) resolutionService.getUserResList(u);
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < 371 ; i++){
            res.add(0);
        }

        for(int i = 0 ; i < url.size() ; i++){
            for(int j = 0; j < url.get(i).getListe().size();j++){
                final int nb_jours = nbDaysBetweenDate(today,url.get(i).getListe().get(j).getDate());
                if( nb_jours <= 371) {
                    res.set(nb_jours,url.get(i).getListe().get(j).getNb_do());
                }
            }
        }
        Collections.reverse(res);
        return res;
    }

    @GetMapping(value = "/api/githubRes")
    public ArrayList<Integer> githubRes(@RequestParam String username,@RequestParam int id){
        UserClass u = resolutionService.getUser(username);
        Date today = new Date(System.currentTimeMillis());
        ArrayList<UserRes> url = (ArrayList<UserRes>) resolutionService.getUserResList(u);
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0 ; i < 371 ; i++){
            res.add(0);
        }

        for(int i = 0 ; i < url.size() ; i++){
            for(int j = 0; j < url.get(i).getListe().size();j++){
                final int nb_jours = nbDaysBetweenDate(today,url.get(i).getListe().get(j).getDate());
                if( nb_jours <= 371) {
                    if (url.get(i).getResId()==id){
                        res.set(nb_jours,url.get(i).getListe().get(j).getNb_do());
                    }
                }
            }
        }
        Collections.reverse(res);
        return res;
    }

    public static int nbDaysBetweenDate(Date d,Date d1){
        long diff = d.getTime() - d1.getTime();
       return (int) (diff / (1000*60*60*24));
    }

    @GetMapping(value = "/api/myResolution")
    public List<Resolution> myResolution(@RequestParam String username){
        List<Resolution> l = resolutionService.myResolution(username);
        if(l.size()>0) {
            Collections.sort(l, new Comparator<Resolution>(){
                public int compare(Resolution o1, Resolution o2){
                    return (int) (o2.getId() - o1.getId());
                }
            });
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

    @GetMapping(value = "/api/getUserRes")
    @ResponseBody
    public InfoResolution getUserRes(@RequestParam String username, @RequestParam int id){
        UserRes r = resolutionService.getUserRes(username,id);
        return new InfoResolution(r.getStart_date(),r.getListe(),"dd-MM-yyyy",r.isAccomplish(new Date(System.currentTimeMillis())));
    }
    @GetMapping(value = "/api/haveResolution")
    @ResponseBody
    public boolean haveResolution(@RequestParam String username, @RequestParam int id){
        return resolutionService.haveResolution(username,id);
    }
    @GetMapping(value = "/api/done")
    @ResponseBody
    public InfoResolution done(@RequestParam String username, @RequestParam int id, @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date date){
        UserRes r = resolutionService.setUserRes(username,id,1,date);
        return new InfoResolution(r.getStart_date(),r.getListe(),"dd-MM-yyyy",r.isAccomplish(new Date(System.currentTimeMillis())));
    }
    @GetMapping(value = "/api/failed")
    @ResponseBody
    public InfoResolution failed(@RequestParam String username, @RequestParam int id,  @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date date){
        UserRes r = resolutionService.setUserRes(username,id,-1,date);
        return new InfoResolution(r.getStart_date(),r.getListe(),"dd-MM-yyyy",r.isAccomplish(new Date(System.currentTimeMillis())));
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
                    ArrayList<UserRes> lur = (ArrayList<UserRes>) resolutionService.getUserResList(ul.get(i));
                    for(int j = 0 ; j < lur.size();j++){
                        resolutionService.removeUserRes(lur.get(j));
                    }
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
