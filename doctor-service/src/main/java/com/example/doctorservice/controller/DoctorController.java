package com.example.doctorservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @GetMapping("/ping")
    @PreAuthorize("hasRole('DOCTOR')")
    public String ping() {
        return "doctor-service is alive";
    }
}
