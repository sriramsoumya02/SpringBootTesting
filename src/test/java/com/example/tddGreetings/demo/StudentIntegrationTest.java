package com.example.tddGreetings.demo;

import com.example.tddGreetings.demo.entity.Student;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@Sql(scripts = "/data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentIntegrationTest {
    @LocalServerPort
    private int port;

    HttpHeaders httpHeaders = new HttpHeaders();

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Test
    public void getStudentByIdTest() throws JSONException {
        HttpEntity entity = new HttpEntity(null, httpHeaders);
        ResponseEntity<String> result = testRestTemplate.exchange("http://localhost:" + port + "/students/1001", HttpMethod.GET, entity, String.class);
        String expected = "{id:1001,name:Soumya,description:10thClass}";
        JSONAssert.assertEquals(expected, result.getBody(), false);
    }

    @Test
    public void createStudentTest() throws JSONException {
        Student s = new Student("harsha", "12th class");
        HttpEntity<Student> entity = new HttpEntity(s, httpHeaders);
        ResponseEntity<String> result = testRestTemplate.exchange("http://localhost:" + port + "/students", HttpMethod.POST, entity, String.class);
        String actual = result.getHeaders().getLocation().toString();
        System.out.println("location:" + actual);
        assertTrue(actual.contains("/students/"));
    }
}
