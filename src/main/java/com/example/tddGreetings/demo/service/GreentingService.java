package com.example.tddGreetings.demo.service;

import org.springframework.stereotype.Service;

@Service
public class GreentingService {
    public String getSalute(String gender) {
        if ("Male".equalsIgnoreCase(gender)) {
            return "Mr.";
        } else if ("Female".equalsIgnoreCase(gender)) {
            return "Mrs";
        } else {
            throw new RuntimeException("Garbage values not allowed");
        }
    }
}
