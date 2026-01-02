package com.example.auth_service.client;

import com.example.auth_service.dto.DoctorProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "doctor-service")
public interface DoctorClient {

    @PostMapping("/api/doctor/create")
    void createDoctorProfile(@RequestBody DoctorProfileDto request);
}
