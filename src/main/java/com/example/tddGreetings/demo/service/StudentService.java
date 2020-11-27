package com.example.tddGreetings.demo.service;

import com.example.tddGreetings.demo.entity.Student;
import com.example.tddGreetings.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student getStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            return null;
        }
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
