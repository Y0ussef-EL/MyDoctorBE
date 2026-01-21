package com.example.appointmentservice.service;

import com.example.appointmentservice.client.DoctorClient;
import com.example.appointmentservice.client.PatientClient;
import com.example.appointmentservice.dto.DoctorDto;
import com.example.appointmentservice.dto.PatientDto;
import com.example.appointmentservice.model.Appointment;
import com.example.appointmentservice.repository.AppointmentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorPatientListService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;

    public DoctorPatientListService(AppointmentRepository appointmentRepository, DoctorClient doctorClient, PatientClient patientClient) {
        this.appointmentRepository = appointmentRepository;
        this.doctorClient = doctorClient;
        this.patientClient = patientClient;
    }

    public List<DoctorDto> patientsListDoctors() {
        List<DoctorDto> doctorDtoList = doctorClient.fetchAllDoctors();
        List<DoctorDto> doctorDtoListFiltered = new ArrayList<DoctorDto>();
        String patientUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Appointment> patientAppointments = appointmentRepository.findByPatientUsername(patientUsername);
        List<String> doctorsUsername = new ArrayList<String>();
        for  (Appointment appointment : patientAppointments) {
            doctorsUsername.add(appointment.getDoctorUsername());
        }
        for(DoctorDto doctorDto : doctorDtoList) {
            if(doctorsUsername.contains(doctorDto.getUsername())) {
                doctorDtoListFiltered.add(doctorDto);
            }
        }
        return doctorDtoListFiltered;
    }
    public List<PatientDto> doctorsListPatients() {
        List<PatientDto> patientDtoList = patientClient.fetchAllPatients();
        List<PatientDto> patientDtoListFiltered = new ArrayList<PatientDto>();
        String doctorUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Appointment> doctorAppointments = appointmentRepository.findByDoctorUsername(doctorUsername);
        List<String> patientsUsername = new ArrayList<String>();
        for (Appointment appointment : doctorAppointments) {
            patientsUsername.add(appointment.getPatientUsername());
        }
        for (PatientDto patientDto : patientDtoList ) {
            if (patientsUsername.contains(patientDto.getUsername())) {
                patientDtoListFiltered.add(patientDto);
            }
        }


        return patientDtoListFiltered;
    }
}
