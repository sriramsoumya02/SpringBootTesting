package com.example.tddGreetings.demo.controller;

import com.example.tddGreetings.demo.entity.Car;
import com.example.tddGreetings.demo.exception.CarNotFoundException;
import com.example.tddGreetings.demo.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CarController.class)
//it will load only mvc related singleton classes instead entire context(springboottest)
public class CarControllerTest {
    @Autowired
    MockMvc mockMvc;//we can trigger the urls using perform operation and see how it will give the response

    @MockBean
    CarService carService;

    @Test
    public void getCarByName() throws Exception {
        String name = "Scala";
        String type = "Sedan";
        Car car = new Car(name, type);
        when(carService.getCarDetails(name)).thenReturn(car);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/cars/" + name));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("type").value(type));
    }

    @Test
    public void getCarByNameThrowExceptionWhenNotFound() throws Exception {
        String name = "Scala";
        when(carService.getCarDetails(name)).thenThrow(CarNotFoundException.class);
        // when(carService.getCarDetails(name)).thenReturn(null);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/cars/" + name));
        resultActions.andExpect(status().isNotFound());
    }

}
