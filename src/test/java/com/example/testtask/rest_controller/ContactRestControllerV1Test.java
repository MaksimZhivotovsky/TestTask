package com.example.testtask.rest_controller;

import com.example.testtask.model.Client;
import com.example.testtask.model.Contact;
import com.example.testtask.service.impl.ContactServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Named;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactRestControllerV1Test {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ContactServiceImpl contactService;

    private Client ivanov = new Client();
    private Client petrov = new Client();
    private Contact contactIvanov = new Contact();
    private Contact contactPetrov = new Contact();


    @BeforeEach
    void setUp() {
        ivanov.setId(1L);
        ivanov.setName("Ivanov");
        contactIvanov.setId(1L);
        contactIvanov.setPhoneNumber("+70987654321");
        contactIvanov.setEmail("ivanov@mail.ru");
        contactIvanov.setClient(ivanov);

        petrov.setId(2L);
        petrov.setName("Petrov");
        contactPetrov.setId(2L);
        contactPetrov.setPhoneNumber("+71234567890");
        contactPetrov.setEmail("petrov@mail.ru");
        contactPetrov.setClient(ivanov);

        contactService.save(contactIvanov);
        contactService.save(contactPetrov);
    }

    @Test
    void getAllContacts() throws Exception {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contactIvanov);
        contacts.add(contactPetrov);

        when(contactService.getAllContacts(2L)).thenReturn(contacts);

        this.mockMvc.perform(get("/api/v1/clients/2/contact")) // check HTTP status
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].length()", is(3)))
                .andExpect(jsonPath("$[1].phoneNumber").value("+71234567890"))
                .andExpect(jsonPath("$[0].email").value("ivanov@mail.ru"))
                .andExpect(jsonPath("$[1].email").value("petrov@mail.ru"))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    void getAllPhoneContacts() throws Exception {

        List<String> contacts = new ArrayList<>();
        contacts.add(contactIvanov.getPhoneNumber());

        when(contactService.getAllPhoneContacts(1L)).thenReturn(contacts);

        Long ivanovId = ivanov.getId();
        this.mockMvc.perform(get("/api/v1/clients/{id}/phone", ivanovId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0]", is("+70987654321")));
    }

    @Test
    void getAllEmailContacts() throws Exception {

        List<String> contacts = new ArrayList<>();
        contacts.add(contactPetrov.getEmail());

        when(contactService.getAllEmailContacts(2L)).thenReturn(contacts);

        Long petrovId = petrov.getId();
        this.mockMvc.perform(get("/api/v1/clients/{id}/email", petrovId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0]", is("petrov@mail.ru")));
    }

    @Test
    void save() throws Exception {
        when(contactService.save(any((Contact.class)))).thenReturn(contactIvanov);

        this.mockMvc.perform(post("/api/v1/clients/1").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(contactIvanov)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.phoneNumber").value("+70987654321"))
                .andExpect(jsonPath("$.email").value("ivanov@mail.ru"))
                .andExpect(jsonPath("$.id", is(1)));
    }
}