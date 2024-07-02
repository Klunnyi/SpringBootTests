package com.example.demo.service;

public class PasswordEncoder {

    public String encode(String rawPassword) {
        return String.format("secret: %s", rawPassword);
    }
}
