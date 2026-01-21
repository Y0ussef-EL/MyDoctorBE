package com.example.appointmentservice.controller;

import com.example.appointmentservice.dto.CreateAppointmentRequest;
import com.example.appointmentservice.dto.UpdateAppointmentStatusRequest;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService=appointmentService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Appointment> create(
            @RequestBody CreateAppointmentRequest request
    ) {
        return ResponseEntity.ok(appointmentService.createAppointment(request));
    }

    @GetMapping("/doctor/appointment")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Appointment> getDoctorAppointments(){
        System.out.println("controller hit succesfully");
        return appointmentService.getDoctorsAppointments();
    }
    @GetMapping("/patient/appointment")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Appointment> getPatientAppointments(){
        System.out.println("controller hit succesfully");
        return appointmentService.getPatientsAppointments();
    }
    @PutMapping("/doctor/status")
    @PreAuthorize("hasRole('DOCTOR')")
    public Appointment updateAppointmentStatus(@RequestBody UpdateAppointmentStatusRequest request) {
        return appointmentService.updateAppointmentStatus(request);
    }

}
