package com.example.testtask.service.impl;

import com.example.testtask.model.Client;
import com.example.testtask.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientServiceImpl clientService;
    private Client client = new Client();
    @BeforeEach
    void setup(){
        client.setId(1L);
        client.setName("Ivanov");
    }

    @Test
    void save() {
        clientService.save(client);
        verify(clientRepository).save(client);
    }

    @Test
    void getAllClientsTest() {
        when(clientRepository.findAll()).thenReturn(List.of(client));
        List<Client> clients = clientService.getAllClients();
        assertEquals(1, clients.size());
        verify(clientRepository).findAll();
    }

    @Test
    void findById() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        Client returnClient = clientService.findById(1L);
        assertEquals(client.getId(), returnClient.getId());
    }
}