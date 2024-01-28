--liquibase formatted sql
--changeSet dev_0001:001-01

INSERT INTO users (full_name, role_id, password, date_of_creation)
values ('Ivan', 1, 'password', '12-12-2023');



