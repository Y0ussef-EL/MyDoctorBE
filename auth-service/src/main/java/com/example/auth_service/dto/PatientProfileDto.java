package com.example.auth_service.dto;

import java.util.Date;

public class PatientProfileDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private String gender;

    public PatientProfileDto(String username, String firstName, String lastName, String email, Date birthDate, String gender) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }
}
