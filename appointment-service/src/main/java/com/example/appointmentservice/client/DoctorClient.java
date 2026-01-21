package com.example.appointmentservice.client;

import com.example.appointmentservice.dto.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "doctor-service")
public interface DoctorClient {
    @GetMapping("/api/doctor/alldoctors")
    List<DoctorDto> fetchAllDoctors();
}
