package com.example.doctorservice.repository;

import com.example.doctorservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByUsername(String username);
}
