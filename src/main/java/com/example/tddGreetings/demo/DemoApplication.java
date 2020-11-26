package com.example.tddGreetings.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner setUp(TodoRepository toDoRepository) {
//
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                toDoRepository.save(new Todo("Add a new test case", true));
//                toDoRepository.save(new Todo("Make it fail", true));
//                toDoRepository.save(new Todo("Do changes to the code", false));
//                toDoRepository.save(new Todo("Pass the test", true));
//            }
//        };
//    }
}
