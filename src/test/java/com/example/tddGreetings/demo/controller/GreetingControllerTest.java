package com.example.tddGreetings.demo.controller;

import com.example.tddGreetings.demo.service.GreentingService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
//@ExtendWith is a repeatable annotation that is used to register extensions for the annotated test class or test method.
@WebMvcTest(GreetingController.class)
//@WebMvcTest annotation is used for Spring MVC tests.
// It disables full auto-configuration and instead apply only configuration relevant to MVC tests.
//The WebMvcTest annotation auto-configure MockMvc instance as well.
public class GreetingControllerTest {
    @Autowired
    MockMvc mockMvc;//which helps in testing the controllers explicitly starting a Servlet container.
    /*
    * @MockBean annotation
It allow to mock a class or an interface and to record and verify behaviors on it.
It can be used as a class level annotation or on fields in either @Configuration classes, or test classes that are @RunWith the SpringRunner.
    * */
    @MockBean
    GreentingService greentingService;

    @Test
    public void maleGreetingTestOk() throws Exception {
        String name = "Rishi";
        String gender = "Male";
        when(greentingService.getSalute(anyString())).thenReturn("Mr.");
        mockMvc.perform(
                MockMvcRequestBuilders.get("/greeting")
                        .param("name", name)
                        .param("gender", gender)).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(String.format("Hello Mr. %s. How are you?", name))));
        verify(greentingService).getSalute(gender);
    }

    @Test
    public void femaleGreetingTestOK() throws Exception {
        String name = "Soumya";
        String gender = "Female";
        when(greentingService.getSalute(gender)).thenReturn("Mrs.");
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting")
                .param("name", name)
                .param("gender", gender))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(String.format("Hello Mrs. %s. How are you?", name))));
        verify(greentingService).getSalute(gender);
    }
}
