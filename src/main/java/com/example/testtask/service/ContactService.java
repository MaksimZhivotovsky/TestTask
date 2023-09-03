package com.example.testtask.service;

import com.example.testtask.model.Contact;
import java.util.List;

public interface ContactService {
    Contact save(Contact contact);
    List<Contact> getAllContacts(Long id);
    List<String> getAllPhoneContacts(Long id);
    List<String> getAllEmailContacts(Long id);
}
