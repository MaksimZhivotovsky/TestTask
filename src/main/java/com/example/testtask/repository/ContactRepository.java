package com.example.testtask.repository;

import com.example.testtask.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByClient_Id(Long id);
}
