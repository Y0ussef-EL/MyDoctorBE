package com.example.appointmentservice.controller;

import com.example.appointmentservice.dto.DoctorDto;
import com.example.appointmentservice.dto.PatientDto;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.service.AppointmentService;
import com.example.appointmentservice.service.DoctorPatientListService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctorpatientlist")
public class DoctorPatientListController {
    private final DoctorPatientListService doctorPatientListService;
    public DoctorPatientListController(DoctorPatientListService doctorPatientListService) {
        this.doctorPatientListService=doctorPatientListService;
    }

    @GetMapping("/doctor/list")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<PatientDto> getDoctorsListPatients(){
        System.out.println("controller hit succesfully");
        return doctorPatientListService.doctorsListPatients();
    }
    @GetMapping("/patient/list")
    @PreAuthorize("hasRole('PATIENT')")
    public List<DoctorDto> getPatientsListDoctors(){
        System.out.println("controller hit succesfully");
        return doctorPatientListService.patientsListDoctors();
    }
}
