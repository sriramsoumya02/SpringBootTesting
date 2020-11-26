package com.example.tddGreetings.demo;

import com.example.tddGreetings.demo.entity.Car;
import com.example.tddGreetings.demo.entity.Todo;
import com.example.tddGreetings.demo.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Sql(scripts = "/data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    CarService carService;

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    @Test
    public void getCarDetails() {
        ResponseEntity<Car> responseEntity = testRestTemplate.exchange("http://localhost:" + port + "/cars/duster", HttpMethod.GET, entity, Car.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().getName(), "duster");
        assertEquals(responseEntity.getBody().getType(), "hybrid");
    }

    @Test
    public void getCarNotFound() {
        ResponseEntity<Car> responseEntity = testRestTemplate.exchange("http://localhost:" + port + "/cars/dfgdfg", HttpMethod.GET, entity, Car.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getGreetings() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:" + port + "/greeting") // rawValidURl = http://example.com/hotels
                .queryParam("name", "soumya")
                .queryParam("gender", "Female"); // The allRequestParams must have been built for all the query params
        UriComponents uriComponents = builder.build().encode(); // encode() is to ensure that characters like {, }, are preserved and not encoded. Skip if not needed.
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().indexOf("soumya") > 0);
        assertTrue(responseEntity.getBody().indexOf("Mrs") > 0);
    }

    @Test
    public void getAllTodosTest() {
        /*
         * if the API call is returning List of Employee objects as JSON then you can directly parse that JSON into List<Employee> object by using ParameterizedTypeReference
         * */
        ResponseEntity<List<Todo>> responseEntity = testRestTemplate.exchange("http://localhost:" + port + "/api/todo", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Todo>>() {
        });
        List<Todo> todos = responseEntity.getBody();
        System.out.println(responseEntity.getBody());
        assertTrue(todos.size() > 0);
    }

    @Test
    public void createTodoTest() {
        Todo todo = new Todo(5L, "pray daily", true);
        HttpEntity<Todo> postEntity = new HttpEntity<>(todo, headers);
        ResponseEntity<Todo> responseEntity = testRestTemplate.exchange("http://localhost:" + port + "/api/todo", HttpMethod.POST, postEntity, Todo.class);
        System.out.println(responseEntity.getBody());
        assertEquals(responseEntity.getBody().getText(), todo.getText());
    }

    @Test
    void contextLoads() {
    }

}
