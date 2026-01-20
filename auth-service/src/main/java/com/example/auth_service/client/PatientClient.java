package com.example.auth_service.client;

import com.example.auth_service.dto.PatientProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="patient-service")
public interface PatientClient {

    @PostMapping("api/patient/create")
    void createPatientProfile(@RequestBody PatientProfileDto request);
}
