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
    tipo_cadastro VARCHAR(255),
    FOREIGN KEY (endereco_id) REFERENCES enderecos(id)
);

INSERT INTO usuarios (nome, email, login) VALUES ('Carolina', 'carolpsa.unesp@gmail.com', 'carol.psa');