package com.example.doctorservice.service;

import com.example.doctorservice.dto.DoctorDTO;
import com.example.doctorservice.model.Doctor;
import com.example.doctorservice.model.Specialization;
import com.example.doctorservice.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void createDoctor(DoctorDTO request) {
        System.out.println("Received create request for: " + request.getUsername());

        Doctor doctor = new Doctor();
        doctor.setUsername(request.getUsername());
        doctor.setFirstName(request.getFirstName());
        doctor.setLastName(request.getLastName());
        doctor.setEmail(request.getEmail());
        try {
            doctor.setSpecialization(
                    Specialization.valueOf(request.getSpecialization().toUpperCase())
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid specialization: " + request.getSpecialization());
            doctor.setSpecialization(Specialization.GENERAL); // Fallback
        }

        doctorRepository.save(doctor);
    }
}
