CREATE DATABASE dbagendaweb;

USE dbagendaweb;

CREATE TABLE contatos (
	id int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    fone VARCHAR(15) NOT NULL,
    email VARCHAR(50),
    aniversario DATE
);

DESCRIBE contatos;

-- RENAME TABLE contato TO contatos; // havia criado a tabela por nome contato

