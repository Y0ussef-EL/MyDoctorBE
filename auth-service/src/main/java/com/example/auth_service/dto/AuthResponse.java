package com.example.auth_service.dto;

import com.example.auth_service.model.Role;

public class AuthResponse {
    public String token;
    public Role role;
    public AuthResponse(String token, Role role) {
        this.token = token;
        this.role = role;
    }
}
