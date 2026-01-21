package com.example.appointmentservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CreateAppointmentRequest {
    private String patientUsername;
    private String doctorUsername;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private String note;

    public CreateAppointmentRequest(String patientUsername, String doctorUsername, LocalDateTime date, String note) {
        this.patientUsername = patientUsername;
        this.doctorUsername = doctorUsername;
        this.date = date;
        this.note = note;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }
}
