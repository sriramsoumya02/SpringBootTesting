package com.example.tddGreetings.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
// all the beans that is required to run this test will be provided by us and spring doesn't look in the scan all the class path
public class GreentingServiceTest {
    @Configuration
    public static class Config {
        @Bean
        public GreentingService greentingService() {
            return new GreentingService();
        }

    }

    @Autowired
    GreentingService greentingService;

    @Test
    public void shouldReturnMrWhenGenderisMale() {
        String salute = greentingService.getSalute("Male");
        assertEquals("Mr.", salute);
    }

    @Test
    public void shouldReturnMrsWhenGenderisFemale() {
        String salute = greentingService.getSalute("Female");
        assertEquals("Mrs", salute);
    }

    @Test
    public void shouldThrowExceptionWhenGenderisAnonymus() {
        assertThrows(RuntimeException.class, () -> greentingService.getSalute("fghfhgf"), "garbage values not allowed");
    }

}
