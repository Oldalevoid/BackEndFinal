package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/api/clienti/prova")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(consumes = "application/json")
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    @GetMapping
    public List<Cliente> getAllClienti() {
        return clienteService.getAllClienti();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

  

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }
}
