package com.example.testtask.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "clients")
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"name"})
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Сущность клиента")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    @Schema(description = "Имя клиента", example = "A-Я, A-Z, 0-9")
    private String name;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @Schema(description = "Контакты клиента",
            example = "phone_number : начинаться с +7, затем - 10 цифр, " +
                    "email : Email должен быть корректным адресом электронной почты")
    private List<Contact> contacts;

    public Client(String name) {
        this.name = name;
    }

    public void addContract(Contact contact) {
        contacts.add(contact);
    }
}