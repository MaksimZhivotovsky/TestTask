create table clients
(
    id   bigserial
        primary key,
    name varchar(255)
);

create table contacts
(
    client_id    bigint
        constraint fk_client_id
            references clients,
    id           bigserial
        primary key,
    email        varchar(255),
    phone_number varchar(255)
);

INSERT INTO clients(name)
VALUES ('Petrov'), ( 'Ivanov'), ('Sidorov');

INSERT INTO contacts( client_id, email, phone_number)
VALUES  (1, 'petrov@mail.ru', '+79150758209'),
        (1, null, '+71234567890'),
        (2, 'ivanov@mail.ru', '+79045789234'),
        (2, 'ivanov.ivanov@mail.ru', '+79877753459'),
        (3, 'sidorov@mail.ru', null),
        (3, 'petrovich@mail.ru', '+79150758209');