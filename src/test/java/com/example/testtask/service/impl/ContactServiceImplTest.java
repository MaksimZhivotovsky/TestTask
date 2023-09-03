package com.example.testtask.service.impl;

import com.example.testtask.model.Client;
import com.example.testtask.model.Contact;
import com.example.testtask.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {
    @Mock
    private ContactRepository contactRepository;
    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    void save() {
        Contact contact = buildTestingContact();
        contactService.save(contact);
        verify(contactRepository).save(contact);
    }

    @Test
    void getAllContacts() {
        Contact contact = buildTestingContact();
        when(contactRepository.findAllByClient_Id(1L)).thenReturn(List.of(contact));
        List<Contact> contacts = contactService.getAllContacts(1L);
        assertEquals(1, contacts.size());
        verify(contactRepository).findAllByClient_Id(1L);
    }
    private Contact buildTestingContact() {
        Client client = new Client();
        client.setId(1L);
        client.setName("Ivanov");
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setEmail("ivanov@mail.ru");
        contact.setPhoneNumber("+70987654321");
        contact.setClient(client);
        return contact;
    }
}