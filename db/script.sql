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

-- SELECT CONTATO ESPECIFICO
SELECT * FROM contatos WHERE id = ?;

-- CRUD / UPDATE - UPDATE
UPDATE contatos SET nome = 'Hellysmar', email = 'iceroot@mail.com' WHERE id = 2;

-- CRUD / DELETE -
DELETE FROM contatos WHERE id = 1;