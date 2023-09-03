package com.example.testtask.rest_controller;

import com.example.testtask.model.Client;
import com.example.testtask.model.Contact;
import com.example.testtask.service.ClientService;
import com.example.testtask.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/clients/{id}")
@AllArgsConstructor
@Tag(name="Контакты", description="Позволяет взаимодействовать с 'контактами' клиента")
public class ContactRestControllerV1 {
    private final ContactService contactService;
    private final ClientService clientService;

    @Operation(
            summary = "Получение списка контактов",
            description = "Получение списка контактов заданного клиента"
    )
    @GetMapping(value = "/contact")
    public ResponseEntity<List<Contact>> getAllContacts(
            @Parameter(description = "Идентификатор клиента")
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(contactService.getAllContacts(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Получение списка телефонных номеров",
            description = "Получение списка контактов заданного клиента телефонных номеров"
    )
    @GetMapping(value = "/phone")
    public ResponseEntity<List<String>> getAllPhoneContacts(
            @Parameter(description = "Идентификатор клиента")
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(contactService.getAllPhoneContacts(id), HttpStatus.OK);
    }
    @Operation(
            summary = "Получение списка адресов электронной почты",
            description = "Получение списка адресов электронной почты заданного клиента"
    )
    @GetMapping(value = "/email")
    public ResponseEntity<List<String>> getAllEmailContacts(
            @Parameter(description = "Идентификатор клиента")
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(contactService.getAllEmailContacts(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Добавление нового контакта клиента",
            description = "Добавление нового контакта клиента (телефон или email)"
    )
    @PostMapping
    public ResponseEntity<Contact> save(
            @Parameter(description = "Идентификатор клиента")
            @PathVariable("id") Long id,
            @Valid @RequestBody Contact contact) {
        Client client = clientService.findById(id);
        if(client != null) {
            contact.setClient(client);
        }
        return new ResponseEntity<>(contactService.save(contact), HttpStatus.CREATED);
    }
}
