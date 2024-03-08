package com.example.demo.service;



import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> getDefaultRole() {
        return roleRepository.findByName("ROLE_USER");
    }
}
