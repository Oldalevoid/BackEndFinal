package com.example.demo.service;

import com.example.demo.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}