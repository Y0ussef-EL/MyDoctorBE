package com.example.appointmentservice.dto;

import com.example.appointmentservice.model.Gender;

import java.util.Date;

public class PatientDto {private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private Gender gender;

    public PatientDto(String username, String firstName, String lastName, String email, Date birthDate, Gender gender) {
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

    public Gender getGender() {
        return gender;
    }
}
