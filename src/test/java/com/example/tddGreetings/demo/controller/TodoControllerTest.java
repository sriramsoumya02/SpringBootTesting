package com.example.tddGreetings.demo.controller;

import com.example.tddGreetings.demo.entity.Todo;
import com.example.tddGreetings.demo.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoService todoService;

    @Test
    public void getAllTodos() throws Exception {
        List<Todo> todosList = Arrays.asList(new Todo("Eat thrice", true), new Todo("Sleep Twice", true), new Todo("cook thrice", true));
        Mockito.when(todoService.findAll()).thenReturn(todosList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3))).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createTodo() throws Exception {
        Todo todo = new Todo(1L, "Eat thrice", true);
        Mockito.when(todoService.save(any(Todo.class))).thenReturn(todo);
        ObjectMapper objectMapper = new ObjectMapper();
        String todoJson = objectMapper.writeValueAsString(todo);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo").contentType(MediaType.APPLICATION_JSON).content(todoJson));
        resultActions.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("Eat thrice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value(true))
                .andDo(MockMvcResultHandlers.print());

    }
}
