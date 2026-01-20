package com.example.auth_service.service;

import com.example.auth_service.client.DoctorClient;
import com.example.auth_service.client.PatientClient;
import com.example.auth_service.dto.AuthResponse;
import com.example.auth_service.dto.DoctorProfileDto;
import com.example.auth_service.dto.PatientProfileDto;
import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.model.Role;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, DoctorClient doctorClient, PatientClient patientClient) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.doctorClient = doctorClient;
        this.patientClient = patientClient;
    }

    public AuthResponse login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));


        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }
        else{
            String token = jwtService.generateToken(user.getUsername(),user.getRole());

            return new AuthResponse(token, user.getRole());

        }

    }
    public void logout(String token) {

    }
    public void register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        if (request.getRole() == Role.DOCTOR) {

            DoctorProfileDto profileDto = new DoctorProfileDto(
                    request.getUsername(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getSpecialization()
            );

            doctorClient.createDoctorProfile(profileDto);
        }
        if (request.getRole() == Role.PATIENT) {
            PatientProfileDto patientDto = new PatientProfileDto(
                    request.getUsername(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getBirthDate(),
                    request.getGender()
            );
            patientClient.createPatientProfile(patientDto);
        }
    }
}
