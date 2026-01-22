package com.example.appointmentservice.controller;

import com.example.appointmentservice.dto.MedicalRecordRequest;
import com.example.appointmentservice.dto.PatientUsernameMedicalRecordRequest;
import com.example.appointmentservice.model.MedicalRecord;
import com.example.appointmentservice.service.MedicalRecordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicalrecord")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('DOCTOR')")
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecordRequest request) {
        return medicalRecordService.createRecord(request);
    }

    @GetMapping("/patientrecord")
    @PreAuthorize("hasAnyRole('PATIENT')")
    public List<MedicalRecord> getMedicalRecordsByPatient() {
        return medicalRecordService.getMedicalRecordsofPatient();
    }
    @GetMapping("/patientrecordfordoctor")
    @PreAuthorize("hasAnyRole('DOCTOR')")
    public List<MedicalRecord> getMedicalRecordsByPatientforDocor(@RequestBody PatientUsernameMedicalRecordRequest request) {
        return medicalRecordService.getMedicalRecordsofPatientforDoctor(request);
    }
}
