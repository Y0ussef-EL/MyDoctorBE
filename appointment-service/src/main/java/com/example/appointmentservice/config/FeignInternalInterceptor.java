package com.example.appointmentservice.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignInternalInterceptor {
    @Bean
    public RequestInterceptor internalHeaderInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Internal-Service", "appointment-service");
        };
    }
}
