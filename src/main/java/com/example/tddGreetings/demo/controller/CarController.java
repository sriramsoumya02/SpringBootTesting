package com.example.tddGreetings.demo.controller;

import com.example.tddGreetings.demo.entity.Car;
import com.example.tddGreetings.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/cars/{name}")
    public ResponseEntity<Car> getCarByName(@PathVariable String name) {
        return new ResponseEntity<Car>(carService.getCarDetails(name), HttpStatus.OK);
    }
}
