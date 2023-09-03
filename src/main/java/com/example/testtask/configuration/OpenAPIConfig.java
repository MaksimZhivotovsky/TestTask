package com.example.testtask.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Api к тестовому заданию",
                description = "Тестовое задание", version = "1.0.0",
                contact = @Contact(
                        name = "Животовский Максим",
                        email = "maksim.zhivotovsky@yandex.ru"
                )
        )
)
public class OpenAPIConfig {

}
