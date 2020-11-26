package com.example.tddGreetings.demo.service;

import com.example.tddGreetings.demo.entity.Car;
import com.example.tddGreetings.demo.exception.CarNotFoundException;
import com.example.tddGreetings.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public Car getCarDetails(String name) {
        Optional<Car> car = carRepository.findByName(name);
        if (car.isPresent()) {
            return car.get();
        } else {
            throw new CarNotFoundException("Car is not available");
        }

    }
}
