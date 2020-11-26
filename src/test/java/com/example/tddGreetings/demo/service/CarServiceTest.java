package com.example.tddGreetings.demo.service;

import com.example.tddGreetings.demo.entity.Car;
import com.example.tddGreetings.demo.exception.CarNotFoundException;
import com.example.tddGreetings.demo.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarServiceTest {
    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;


    @Test
    public void getcarDetailsTest() {
        String name = "pulse";
        String type = "hatchback";
        when(carRepository.findByName(name)).thenReturn(Optional.of(new Car(name, type)));
        Car car = carService.getCarDetails(name);
        assertEquals(name, car.getName());
        assertEquals(type, car.getType());
    }

    @Test
    public void getcarDetailsShouldThrowCardNotFoundException() {
        String name = "pulse";
        when(carRepository.findByName(name)).thenReturn(Optional.empty());
        assertThrows(CarNotFoundException.class, () -> carService.getCarDetails(name), "car not found");
    }

}
