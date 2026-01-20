package com.example.patientservice.controller;

import com.example.patientservice.dto.DoctorDto;
import com.example.patientservice.dto.PatientDTO;
import com.example.patientservice.service.DoctorServiceClient;
import com.example.patientservice.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final DoctorServiceClient doctorServiceClient;
    private final PatientService patientService;

    public PatientController(DoctorServiceClient doctorServiceClient, PatientService patientService) {
        this.doctorServiceClient = doctorServiceClient;
        this.patientService = patientService;
    }
    @GetMapping("/ping")
    public String ping() {
        return "patient-service is alive";
    }

    @PostMapping("/create")
    public void createDoctor(@RequestBody PatientDTO request) {
        System.out.println("DEBUG: Received First Name: " + request.getFirstName());
        System.out.println("DEBUG: Received Last Name: " + request.getLastName());
        patientService.createPatient(request);
    }

    @GetMapping("/doctors")
    public List<DoctorDto> getDoctors() {
        System.out.println("doc endpoints hit");
        return doctorServiceClient.fetchAllDoctors();
    }
}
