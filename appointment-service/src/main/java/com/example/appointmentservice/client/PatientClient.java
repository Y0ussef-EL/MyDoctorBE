package com.example.appointmentservice.client;


import com.example.appointmentservice.dto.DoctorDto;
import com.example.appointmentservice.dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "patient-service")
public interface PatientClient {
    @GetMapping("/api/patient/allpatients")
    List<PatientDto> fetchAllPatients();
}
