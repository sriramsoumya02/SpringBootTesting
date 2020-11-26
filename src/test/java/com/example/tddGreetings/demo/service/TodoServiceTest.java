package com.example.tddGreetings.demo.service;

import com.example.tddGreetings.demo.entity.Todo;
import com.example.tddGreetings.demo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
//With the @SpringBootTest annotation, Spring Boot provides a convenient way to start up an application context to be used in a test.
public class TodoServiceTest {
    @Mock
    TodoRepository todoRepository;

    @InjectMocks
    TodoService todoService = new TodoService(todoRepository);

    @Test
    public void findAllTest() {
        List<Todo> toDoList = Arrays.asList(new Todo("daily learn something", true));
        Mockito.when(todoRepository.findAll()).thenReturn(toDoList);
        //Todo result = toDoList.get(toDoList.size() - 1);
        Todo result = todoService.findAll().get(0);
        //assertEquals(result.getId(), todo.getId());
        assertEquals(result.getText(), "daily learn something");
        assertTrue(result.isCompleted());

    }

    @Test
    public void createTodo() {
        Todo todo = new Todo("daily learn something", true);
        Todo todo1 = new Todo(3L, "daily learn something", true);
        Mockito.when(todoRepository.save(todo)).thenReturn(todo1);
        Todo result = todoService.save(todo);
        assertThat(result).isNotNull();
        verify(todoRepository).save(any(Todo.class));
    }
}
