package com.example.testtask.service.impl;

import com.example.testtask.exeption.ClientNotFoundException;
import com.example.testtask.model.Client;
import com.example.testtask.repository.ClientRepository;
import com.example.testtask.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client не найден с id : " + id));
    }
}
