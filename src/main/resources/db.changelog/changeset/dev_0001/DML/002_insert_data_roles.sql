--liquibase formatted sql
--changeSet dev_0001:002-01

INSERT INTO roles (role)
values ('USER');

INSERT INTO roles (role)
values ('ADMIN');


