package com.example.appointmentservice.service;

import com.example.appointmentservice.dto.MedicalRecordRequest;
import com.example.appointmentservice.model.MedicalRecord;
import com.example.appointmentservice.repository.MedicalRecordRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord createRecord(MedicalRecordRequest request) {
        MedicalRecord record = new MedicalRecord();
        record.setPatientUsername(request.getPatientUsername());
        record.setDoctorUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        record.setDiagnosis(request.getDiagnosis());
        record.setTreatment(request.getTreatment() != null ? request.getTreatment() : "");
        record.setTreatmentPlan(request.getTreatmentPlan() != null ? request.getTreatmentPlan() : "");
        record.setDuration(request.getDuration() != null ? request.getDuration() : "");
        record.setDate(new Date());

        return medicalRecordRepository.save(record);
    }

    public List<MedicalRecord> getMedicalRecordsofPatient() {
        return medicalRecordRepository.findByPatientUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
