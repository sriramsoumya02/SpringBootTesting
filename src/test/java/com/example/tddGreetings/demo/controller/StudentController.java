package com.example.tddGreetings.demo.controller;

import com.example.tddGreetings.demo.entity.Student;
import com.example.tddGreetings.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id) {
        return new ResponseEntity(studentService.getStudent(id), HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity createStudent(@RequestBody Student student) {
        Student cretedStudent = studentService.saveStudent(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cretedStudent.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
