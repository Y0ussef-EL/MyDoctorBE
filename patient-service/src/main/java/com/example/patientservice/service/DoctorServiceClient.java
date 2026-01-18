package com.example.patientservice.service;

import com.example.patientservice.client.DoctorClient;
import com.example.patientservice.dto.DoctorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceClient {



    private final DoctorClient doctorClient;
    public DoctorServiceClient(DoctorClient doctorClient) {
        this.doctorClient = doctorClient;
    }

    public List<DoctorDto> fetchAllDoctors() {
        return doctorClient.fetchAllDoctors();
    }
}

