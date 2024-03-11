package com.example.demo.config;

import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Logga la richiesta HTTP
        System.out.println("Request URL: " + request.getRequestURL());
        // Esegui il filtro successivo nella catena
        filterChain.doFilter(request, response);
        // Logga la risposta HTTP
        System.out.println("Response Status: " + response.getStatus());
    }
}