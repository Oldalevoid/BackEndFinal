package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento per l'ID
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    private String residenza;
    private String codiceFiscale;

    // Costruttore vuoto richiesto da JPA
    public Customer() {
    }

    // Costruttore con parametri per creare un nuovo cliente
    public Customer(String nome, String cognome, String email, String residenza, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.residenza = residenza;
        this.codiceFiscale = codiceFiscale;
    }

    // Metodi getter e setter per tutti i campi
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
