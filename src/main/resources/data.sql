CREATE TABLE enderecos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255),
    numero VARCHAR(255),
    bairro VARCHAR(255),
    cep VARCHAR(255),
    cidade VARCHAR(255)
);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    login VARCHAR(255),
    senha VARCHAR(255),
    data_alteracao DATE,
    endereco_id BIGINT,
    role VARCHAR(255),
    enabled BOOLEAN,
    FOREIGN KEY (endereco_id) REFERENCES enderecos(id)
);

INSERT INTO enderecos (id, rua, numero, bairro, cep, cidade) VALUES (1, 'Uchoa', '393', 'Xavier', '14811238', 'Araraquara');

INSERT INTO usuarios (id, nome, email, senha, endereco_id, role) VALUES (1, 'Carolina',  'carolpsa@gmail.com', '$2a$10$6eSVfWCW6MLhGHw5SxlTb.rCcdG931Qail0XIfSQ.3dWehytDFfZO', 1, 'DONO');


