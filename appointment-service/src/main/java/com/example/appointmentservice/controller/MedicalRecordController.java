package com.example.appointmentservice.controller;

import com.example.appointmentservice.dto.MedicalRecordRequest;
import com.example.appointmentservice.model.MedicalRecord;
import com.example.appointmentservice.service.MedicalRecordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
