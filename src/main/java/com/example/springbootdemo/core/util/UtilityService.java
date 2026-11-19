package com.example.springbootdemo.core.util;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {
    public String getGreetingMessage(String name) {
        return "Hello, " + name + "! Welcome to the Spring Boot Demo application.";
    }
}
