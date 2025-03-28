package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "Hello, this is my Spring Boot App!";
    }

    @GetMapping("/env")
    public String getEnvVariable() {
        return "DEVOPS=" + System.getenv("DEVOPS");
    }
}