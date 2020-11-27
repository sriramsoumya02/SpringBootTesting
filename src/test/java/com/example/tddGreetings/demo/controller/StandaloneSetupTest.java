package com.example.tddGreetings.demo.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StandaloneSetupTest {

    private static MockMvc mockMvc;

    @BeforeAll
    public static void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new StandaloneSetupTestExample()).build();
    }

    @Test
    public void setupMessageTest() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/setup"));
        resultActions.andExpect(status().isOk()).andExpect(content().string("Hello my test is without extended with and using standalone setup"))
                .andDo(print());
    }
}
