package com.example.newyear;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
public class NewYearApplication {

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
            return Collections.singletonMap("name", principal.getAttribute("name"));
        }else{
            return null;
        }
    }

}
