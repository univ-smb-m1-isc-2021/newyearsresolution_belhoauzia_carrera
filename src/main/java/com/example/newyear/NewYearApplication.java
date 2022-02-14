package com.example.newyear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NewYearApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewYearApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/con")
    public String con(@RequestParam(value = "name", defaultValue = "Thomas") String name) {
        return String.format("Qui est con ? %s!", name);
    }
}
