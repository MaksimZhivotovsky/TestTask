package com.example.testtask.rest_controller;

import com.example.testtask.model.Client;
import com.example.testtask.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j
@RestController
@RequestMapping(value = "/api/v1/clients")
@AllArgsConstructor
@Tag(name="Клиент", description="Взаимодействие с клиентом")
public class ClientRestControllerV1 {

    private final ClientService clientService;

    @Operation(
            summary = "Получение списка клиентов",
            description = "Позволяет показать список клиентов"
    )
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }
    @Operation(
            summary = "Получение клиента по id",
            description = "Получение информации по заданному клиенту (по id)"
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(
            @Parameter(description = "Идентификатор клиента")
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }
    @Operation(
            summary = "Добавление нового клиента",
            description = "Позволяет добавить клиента"
    )
    @PostMapping
    public ResponseEntity<Client> save(@Valid @RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

}
