package com.example.appointmentservice.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String patientUsername;
    @Column(nullable = false)
    private String doctorUsername;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    private String note;

    public Appointment() {
    }

    public Appointment(String patientUsername, String doctorUsername, Date date, Status status, String note) {
        this.patientUsername = patientUsername;
        this.doctorUsername = doctorUsername;
        this.date = date;
        this.status = status;
        this.note = note;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
