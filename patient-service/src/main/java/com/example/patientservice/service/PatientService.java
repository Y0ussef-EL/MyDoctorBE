package com.example.patientservice.service;

import com.example.patientservice.dto.PatientDTO;
import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public PatientDTO getPatient(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Patient pat =patientRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("the patient with the provided username do not exist"));
        return new PatientDTO(pat.getUsername(), pat.getFirstName(),pat.getLastName(),pat.getEmail(),pat.getBirthDate(),pat.getGender());
    }
}
