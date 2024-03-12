package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Customer;

@Repository
public interface ClienteRepository extends JpaRepository<Customer, Long> {
    // Eventuali metodi personalizzati per la gestione dei dati del cliente
}
