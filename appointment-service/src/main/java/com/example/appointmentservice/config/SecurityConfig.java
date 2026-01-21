package com.example.appointmentservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final AuthenticationFilter authenticationFilter;

    public SecurityConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/appointment/create").hasRole("PATIENT")
                        .requestMatchers(HttpMethod.GET,"/api/appointment/doctor/appointment").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/appointment/patient/appointment").hasRole("PATIENT")
                        .requestMatchers(HttpMethod.PUT, "/api/appointment/doctor/status").hasRole("DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/doctorpatientlist/patient/list").hasRole("PATIENT")
                        .requestMatchers(HttpMethod.GET,"/api/doctorpatientlist/doctor/list").hasRole("DOCTOR")



                        .anyRequest().denyAll()
                );

        return http.build();
    }
}

