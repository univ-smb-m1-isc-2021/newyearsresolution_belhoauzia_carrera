package com.example.newyear;

import com.example.newyear.persistence.UserClass;
import com.example.newyear.service.ResolutionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class NewYearApplication {
    private final ResolutionService resolutionService;

    public NewYearApplication(ResolutionService resolutionService) {
        this.resolutionService = resolutionService;
    }

    @GetMapping("/error")
    public String error() {
        return new InitReact().serialize();
    }
    @GetMapping("/perform_login")
    public String login(HttpServletRequest request) {
        return request.getParameter("name");
    }
    @GetMapping(value = "/")
    public String renderMainPage() {
        return new InitReact().serialize();
    }

    @GetMapping("/getError")
    public String getError(HttpServletRequest request) {
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        if(principal!= null) {
            String name = principal.getAttribute("name");
            List<UserClass> ul = resolutionService.userList();
            boolean userexist = false;
            for (int i = 0 ; i < ul.size();i++){
                if(ul.get(i).getUsername().equals(name)){
                    if(!ul.get(i).getIsSocial()){return Collections.singletonMap("erreur", "Un utilisateur posséde déjà ce nom");}
                    userexist = true;
                    break;
                }
            }
            if(!userexist){
                resolutionService.addUser(name, UUID.randomUUID().toString(),true);
            }
            return Collections.singletonMap("name", name);
        }else{
            return null;
        }
    }

}
