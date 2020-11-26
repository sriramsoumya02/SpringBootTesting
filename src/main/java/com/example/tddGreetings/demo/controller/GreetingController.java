package com.example.tddGreetings.demo.controller;

import com.example.tddGreetings.demo.service.GreentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @Autowired
    GreentingService greentingService;

    @RequestMapping("/greeting")
    public ResponseEntity<String> greetings(@RequestParam String name, @RequestParam String gender) {
        String salute = greentingService.getSalute(gender);
        return new ResponseEntity<String>(String.format("Hello %s %s. How are you?", salute, name), HttpStatus.OK);

    }
}
