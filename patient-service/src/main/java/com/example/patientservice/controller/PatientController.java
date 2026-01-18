package com.example.patientservice.controller;

import com.example.patientservice.dto.DoctorDto;
import com.example.patientservice.service.DoctorServiceClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final DoctorServiceClient doctorServiceClient;

    public PatientController(DoctorServiceClient doctorServiceClient) {
        this.doctorServiceClient = doctorServiceClient;
    }
    @GetMapping("/ping")
    public String ping() {
        return "patient-service is alive";
    }

    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors() {
        System.out.println("doc endpoints hit");
        return doctorServiceClient.fetchAllDoctors();
    }
}
