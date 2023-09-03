package com.example.testtask.rest_controller;

import com.example.testtask.model.Client;
import com.example.testtask.model.Contact;
import com.example.testtask.repository.ClientRepository;
import com.example.testtask.service.impl.ClientServiceImpl;
import com.example.testtask.service.impl.ContactServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientRestControllerV1Test {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ClientServiceImpl clientService;

    private Client ivanov = new Client();
    private Client petrov = new Client();

    @BeforeEach
    void setup(){
        ivanov.setId(1L);
        ivanov.setName("Ivanov");

        petrov.setId(2L);
        petrov.setName("Petrov");

        clientService.save(ivanov);
        clientService.save(petrov);
    }


    @Test
    void getAllClientsTest() throws Exception {
        List<Client> clients = new ArrayList<>();
        clients.add(ivanov);
        clients.add(petrov);

        when(clientService.getAllClients()).thenReturn(clients);

        this.mockMvc.perform(get("/api/v1/clients")) // check HTTP status
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("$[0].name").value("Ivanov"))
                .andExpect(jsonPath("$[1].name").value("Petrov"))
                .andExpect(jsonPath("$[0].id", Is.is(1)))
                .andExpect(jsonPath("$[1].id", Is.is(2)));

    }

    @Test
    void findByIdTest() throws Exception {

        Long petrovId = petrov.getId();
        List<Client> clients = new ArrayList<>();
        clients.add(petrov);

        when(clientService.findById(petrovId)).thenReturn(petrov);

        this.mockMvc.perform(get("/api/v1/clients/{id}", petrovId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Petrov"))
                .andExpect(jsonPath("$.id", Is.is(2)));

    }

    @Test
    void saveTest() throws Exception {

        when(clientService.save(any((Client.class)))).thenReturn(ivanov);

        this.mockMvc.perform(post("/api/v1/clients").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ivanov)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ivanov"))
                .andExpect(jsonPath("$.id", Is.is(1)));

    }

}