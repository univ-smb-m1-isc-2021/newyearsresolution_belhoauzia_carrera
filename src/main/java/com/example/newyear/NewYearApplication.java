package com.example.newyear;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewYearApplication {

    @GetMapping("/app")
    public String renderMainPage() {
        return new InitReact().serialize();
    }

    @GetMapping("/app/login")
    public String renderLogin() {
        return new InitReact().serialize();
    }

}
