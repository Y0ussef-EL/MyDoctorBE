package com.example.appointmentservice.dto;

import java.util.Date;

public class MedicalRecordRequest {
    private String patientUsername;
    private String diagnosis;
    private String treatment;
    private String treatmentPlan;
    private String duration;

    public MedicalRecordRequest(String patientUsername, String diagnosis, String treatment, String treatmentPlan, String duration) {
        this.patientUsername = patientUsername;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.treatmentPlan = treatmentPlan;
        this.duration = duration;
    }

    public String getPatientUsername() {
        return patientUsername;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public String getDuration() {
        return duration;
    }

}
