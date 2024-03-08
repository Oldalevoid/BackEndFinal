package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;



@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Retrieve the default role
        Optional<Role> defaultRoleOptional = roleService.getDefaultRole();

        if (defaultRoleOptional.isEmpty()) {
            return new ResponseEntity<>("Default role not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Assign the default role to the user
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRoleOptional.get());
        user.setRoles(roles);

        userService.saveUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
    
   
}

