package com.example.testtask.service.impl;

import com.example.testtask.model.Contact;
import com.example.testtask.repository.ContactRepository;
import com.example.testtask.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
    @Override
    public List<Contact> getAllContacts(Long id) {
        return contactRepository.findAllByClient_Id(id);
    }
    @Override
    public List<String> getAllPhoneContacts(Long id) {
        return contactRepository.findAllByClient_Id(id).stream()
                .map(Contact::getPhoneNumber)
                .filter(Objects::nonNull).toList();
    }
    @Override
    public List<String> getAllEmailContacts(Long id) {
        List<String> emails = new ArrayList<>();
        List<Contact> contacts = contactRepository.findAllByClient_Id(id);
        if (!contacts.isEmpty()) {
            for (Contact contact : contacts) {
                if(contact.getEmail() != null) {
                    emails.add(contact.getEmail());
                }
            }
        }
        return emails;
    }

}
