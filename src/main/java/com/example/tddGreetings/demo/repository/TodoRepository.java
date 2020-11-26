package com.example.tddGreetings.demo.repository;

import com.example.tddGreetings.demo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("todoRepository")
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
