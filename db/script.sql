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

-- RENOMEAR TABELA
-- RENAME TABLE contato TO contatos; // havia criado a tabela por nome contato


-- CRUD / CREATE - INSERT
INSERT INTO contatos (nome, fone, email, aniversario) VALUES ('Hellen', '618545845', 'hellen@mail.com', 20241025);

-- CRUD / READ - SELECT
SELECT * FROM contatos ORDER BY nome;