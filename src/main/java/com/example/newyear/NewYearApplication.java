package com.example.newyear;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collections;
import java.util.Map;

@RestController
public class NewYearApplication {

    @GetMapping(value = "/")
    public String renderMainPage() {
        return new InitReact().serialize();
    }

    @GetMapping(value = "/error")
    public String renderErrorPage() {
        return new InitReact().serialize();
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

}
