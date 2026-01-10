package com.example.auth_service.dto;

public class MeResponse {
    private String username;
    private String role;

    public MeResponse(String role, String username) {
        this.role = role;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
