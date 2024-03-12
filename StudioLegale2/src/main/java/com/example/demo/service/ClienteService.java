package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Customer saveCliente(Customer cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Customer> getAllClienti() {
        return clienteRepository.findAll();
    }

    public Optional<Customer> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Customer updateCliente(Long id, Customer cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
