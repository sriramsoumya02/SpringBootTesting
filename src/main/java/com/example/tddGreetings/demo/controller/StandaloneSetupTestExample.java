package com.example.tddGreetings.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StandaloneSetupTestExample {

    @GetMapping("/setup")
    public String setupMessage() {
        return "Hello my test is without extended with and using standalone setup";
    }
}
