package com.example.newyear;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewYearApplication {

    @GetMapping(value = "/")
    public String renderMainPage() {
        return new InitReact().serialize();
    }
}
