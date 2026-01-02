package com.example.auth_service.dto;

public class DoctorProfileDto {
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String specialization;

    public DoctorProfileDto(String username, String firstName, String lastName, String email, String specialization) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialization = specialization;
    }

    public String getUsername() { return username; }
    public String getFirstname() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getSpecialization() { return specialization; }
}
