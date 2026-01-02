package com.example.doctorservice.controller;

import com.example.doctorservice.dto.DoctorDTO;
import com.example.doctorservice.model.Doctor;
import com.example.doctorservice.service.DoctorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    private final DoctorService  doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/ping")
    @PreAuthorize("hasRole('DOCTOR')")
    public String ping() {
        return "doctor-service is alive";
    }

    @PostMapping("/create")
    public void createDoctor(@RequestBody DoctorDTO request) {
        System.out.println("DEBUG: Received First Name: " + request.getFirstName());
        System.out.println("DEBUG: Received Last Name: " + request.getLastName());
        doctorService.createDoctor(request);
    }

}
