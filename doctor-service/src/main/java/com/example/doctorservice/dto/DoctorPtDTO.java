package com.example.doctorservice.dto;

public class DoctorPtDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String specialization;

    public DoctorPtDTO(String username,String firstName, String lastName, String specialization) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
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

    public String getSpecialization() {
        return specialization;
    }
}
