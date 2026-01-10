package com.example.doctorservice.service;

import com.example.doctorservice.dto.DoctorDTO;
import com.example.doctorservice.dto.DoctorPtDTO;
import com.example.doctorservice.model.Doctor;
import com.example.doctorservice.model.Specialization;
import com.example.doctorservice.repository.DoctorRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public DoctorDTO getDoctor(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Doctor doc = doctorRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("the doctor with the provided username do not exist"));
        return new DoctorDTO(doc.getUsername(),  doc.getFirstName(), doc.getLastName(), doc.getEmail(), doc.getSpecialization().toString());
    }
    public List<DoctorPtDTO> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorPtDTO> dtos = new ArrayList<>();
        for (Doctor doc : doctors) {
            DoctorPtDTO dto = new DoctorPtDTO(doc.getFirstName(), doc.getLastName(),doc.getSpecialization().toString());
            dtos.add(dto);
        }
        return dtos;

    }
}
