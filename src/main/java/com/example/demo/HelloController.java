package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/users")
    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(
                System.getenv("SPRING_DATASOURCE_URL"),
                System.getenv("SPRING_DATASOURCE_USERNAME"),
                System.getenv("SPRING_DATASOURCE_PASSWORD"))) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM Users");

            while (rs.next()) {
                users.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}