package com.example.appointmentservice.dto;

import com.example.appointmentservice.model.Status;

public class UpdateAppointmentStatusRequest {
    private Long id;
    private Status status;

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
}