package com.example.testtask.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"email", "phoneNumber"})
@Table(name = "contacts")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Сущность контакта")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    @Email(message = "Email должен быть корректным адресом электронной почты")
    @Schema(description = "Email клиента", example = "Email должен быть корректным адресом электронной почты")
    private String email;

    @Column(name = "phone_number")
    @Pattern(regexp = "\\+7[0-9]{10}", message = "Телефонный номер должен начинаться с +7, затем - 10 цифр")
    @Schema(description = "Номер телефона клиента", example = "должен начинаться с +7, затем - 10 цифр")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

}
