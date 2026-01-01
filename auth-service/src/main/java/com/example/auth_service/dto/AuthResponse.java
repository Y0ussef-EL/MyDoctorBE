package com.example.auth_service.dto;

public class AuthResponse {
    public String token;
    public AuthResponse(String token) {
        this.token = token;
    }
}
