//package com.example.tddGreetings.demo.cache;
//
//import com.example.tddGreetings.demo.entity.Car;
//import com.example.tddGreetings.demo.repository.CarRepository;
//import com.example.tddGreetings.demo.service.CarService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
// // ****** have to work on this again
//@SpringBootTest
//public class CacheTest {
//    @MockBean
//    CarRepository carRepository;
//
//    @Autowired
//    CarService carService;
//
//    @Test
//    public void SpringBootTest() {
//        String name = "pulse";
//        String type = "hatchback";
//        when(carRepository.findByName(name)).thenReturn(Optional.of(new Car(name, type)));
//        assertNotNull(carService.getCarDetails(name));
//        carService.getCarDetails(name);
//        Mockito.verify(carRepository, Mockito.times(1)).findByName(name);
//    }
//}
