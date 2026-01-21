package com.example.appointmentservice.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String patientUsername;
    @Column(nullable = false)
    private String doctorUsername;
    @Column(nullable = false)
    private String diagnosis;
    private String treatment;
    private String treatmentPlan;
    private String duration;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    public MedicalRecord() {
    }

    public MedicalRecord(String patientUsername, String doctorUsername, String diagnosis, String treatment, String treatmentPlan, String duration, Date date) {
        this.patientUsername = patientUsername;
        this.doctorUsername = doctorUsername;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.treatmentPlan = treatmentPlan;
        this.duration = duration;
        this.date = date;
    }

    public long getId() {
        return id;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
