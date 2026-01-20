package com.example.patientservice.service;

import com.example.patientservice.dto.PatientDTO;
import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public void createPatient(PatientDTO request){
        System.out.println("DEBUG: Creating Patient " + request.getFirstName() + " " + request.getLastName());
        Patient patient = new Patient();
        patient.setUsername(request.getUsername());
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setEmail(request.getEmail());
        patient.setBirthDate(request.getBirthDate());
        patient.setGender(request.getGender());
        patientRepository.save(patient);
    }
}
