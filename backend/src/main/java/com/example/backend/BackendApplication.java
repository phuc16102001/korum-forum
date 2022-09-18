package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BackendApplication {
    static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationWebConfig.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationWebConfig.class, args);
        System.out.println("Spring Boot Application is running");
    }
}
