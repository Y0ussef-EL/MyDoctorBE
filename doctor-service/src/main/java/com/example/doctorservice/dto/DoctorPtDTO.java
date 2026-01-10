package com.example.doctorservice.dto;

public class DoctorPtDTO {
    private String firstName;
    private String lastName;
    private String specialization;

    public DoctorPtDTO(String firstName, String lastName, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
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
