package com.example.appointmentservice.repository;

import com.example.appointmentservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findAllByDoctorUsernameAndDateAfter(String doctorUsername, LocalDateTime date);
}
