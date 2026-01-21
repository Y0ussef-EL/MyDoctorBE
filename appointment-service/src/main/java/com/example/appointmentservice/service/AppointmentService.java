package com.example.appointmentservice.service;

import com.example.appointmentservice.dto.CreateAppointmentRequest;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.model.Status;
import com.example.appointmentservice.repository.AppointmentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    public Appointment createAppointment(CreateAppointmentRequest request){
        if (request.getDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment date must be in the future");
        }
        String patientUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Appointment appointment = new Appointment();
        appointment.setPatientUsername(patientUsername);
        appointment.setDoctorUsername(request.getDoctorUsername());
        appointment.setDate(request.getDate());
        appointment.setNote(request.getNote());
        appointment.setStatus(Status.PENDING);
        return appointmentRepository.save(appointment);
    }
    public List<Appointment> getDoctorsAppointments(){
        String doctorUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return appointmentRepository.findAllByDoctorUsernameAndDateAfter(doctorUsername, LocalDateTime.now());
    }
}
