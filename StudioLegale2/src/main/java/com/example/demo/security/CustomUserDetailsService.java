package com.example.demo.security;

// Import necessari
import lombok.AllArgsConstructor;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

// Import per i log
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Annotation per indicare che questa classe è un servizio Spring
@Service
// Lombok: genera un costruttore con un parametro per ogni campo della classe
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // Logger per questa classe
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    // Iniezione della dipendenza del repository degli utenti
    private UserRepository userRepository;

    // Implementazione del metodo per caricare l'utente tramite nome utente o email
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Log per indicare che il caricamento dell'utente è iniziato
        logger.info("Loading user by username or email: {}", usernameOrEmail);

        // Cerca l'utente nel repository per nome utente o email
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> {
                    // Log se l'utente non è stato trovato
                    logger.error("User not found by Username or Email: {}", usernameOrEmail);
                    return new UsernameNotFoundException("User not exists by Username or Email");
                });

        // Ottiene le autorizzazioni dell'utente e le converte in oggetti GrantedAuthority
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        // Restituisce un oggetto UserDetails che rappresenta l'utente
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                usernameOrEmail, // Nome utente
                user.getPassword(), // Password
                authorities // Autorizzazioni dell'utente
        );

        // Log per indicare che il caricamento dell'utente è stato completato
        logger.info("User loaded successfully: {}", userDetails.getUsername());

        return userDetails;
    }
}
