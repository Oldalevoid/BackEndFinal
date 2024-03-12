package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Customer;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(consumes = "application/json")
    public Customer createCliente(@RequestBody Customer cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public Customer updateCliente(@PathVariable Long id, @RequestBody Customer cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    @GetMapping
    public List<Customer> getAllClienti() {
        return clienteService.getAllClienti();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

  

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }
}
