package com.example.tddGreetings.demo.repository;

import com.example.tddGreetings.demo.entity.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Test
    public void testFindByName() {
        Optional<Car> car = carRepository.findByName("duster");
        assertTrue(car.isPresent());
        assertEquals(car.get().getName(), "duster");
    }

    @Test
    public void testFindByNameUnsuccessfull() {
        Optional<Car> car = carRepository.findByName("fgfgfgf");
        assertFalse(car.isPresent());
    }
}
