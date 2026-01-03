package com.example.gatewayserver.security;

import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        System.out.println("JWT FILTER: Request for path: " + path);

        if (path.startsWith("/auth-service/api/auth/")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("JWT FILTER: No valid Auth header found. Returning 401.");
            return unauthorized(exchange);
        }

        try {
            String token = authHeader.substring(7);
            Claims claims = jwtService.extractClaims(token);

            String role = claims.get("role", String.class);


            String username = claims.getSubject();

            System.out.println("JWT FILTER: Valid Token. User=" + username + ", Role=" + role);

            if (path.startsWith("/doctor-service/") && !"DOCTOR".equals(role)) {
                System.out.println("JWT FILTER: Role mismatch! Expected DOCTOR, got " + role);
                return forbidden(exchange);
            }

            if (username != null && role != null) {
                ServerHttpRequest mutatedRequest = exchange.getRequest()
                        .mutate()
                        .header("X-User-Username", username)
                        .header("X-User-Role", role)
                        .build();

                System.out.println("JWT FILTER: Forwarding with headers injected.");
                return chain.filter(exchange.mutate().request(mutatedRequest).build());
            }

        } catch (Exception e) {
            System.out.println("JWT FILTER: Token validation failed: " + e.getMessage());
            return unauthorized(exchange);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    private Mono<Void> forbidden(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }
}