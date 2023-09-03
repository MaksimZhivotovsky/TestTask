package com.example.testtask.service;

import com.example.testtask.model.Client;
import java.util.List;

public interface ClientService {
    Client save(Client client);
    List<Client> getAllClients();
    Client findById(Long id);
}
