CREATE DATABASE pousada;

USE pousada;

CREATE TABLE principal(
principalInfo VARCHAR(550) NOT NULL,
principalDetalhe VARCHAR(850) NOT NULL,
chaleInfo VARCHAR(550) NOT NULL,
chaleDetalhe VARCHAR(850) NOT NULL,
lazerInfo VARCHAR(550) NOT NULL,
lazerDetalhe VARCHAR(850) NOT NULL,
servicoInfo VARCHAR(550) NOT NULL,
servicoDetalhe VARCHAR(850) NOT NULL,
reservaInfo VARCHAR(550) NOT NULL,
contatoInfo VARCHAR(550) NOT NULL,
versao VARCHAR(10) NOT NULL
 )

CREATE TABLE cliente(
 nome VARCHAR(100) NOT NULL,
 email VARCHAR(30) UNIQUE NOT NULL,
 documento VARCHAR(15) PRIMARY KEY,
 docTipo VARCHAR(15),
 dtNasc DATE,
 telefone VARCHAR(11) UNIQUE,
 celular VARCHAR(12) UNIQUE NOT NULL,
 endereco VARCHAR(200),
 bairro VARCHAR(30),
 cidade VARCHAR(30) NOT NULL,
 estado VARCHAR(15) NOT NULL,
 pais VARCHAR(10) NOT NULL,
 cep VARCHAR(8),
 dtCadastro DATE NOT NULL,
 ativo BOOLEAN NOT NULL
);

CREATE TABLE funcionario (
 nome VARCHAR(100) NOT NULL,
 cpf VARCHAR(11) PRIMARY KEY,
 nascimento DATE NOT NULL,
 telefone VARCHAR(11) UNIQUE,
 celular VARCHAR(12) UNIQUE NOT NULL,
 email VARCHAR(30) UNIQUE NOT NULL,
 endereco VARCHAR(200) NOT NULL,
 bairro VARCHAR(30) NOT NULL,
 cidade VARCHAR(30) NOT NULL,
 estado VARCHAR(2) NOT NULL,
 cep VARCHAR(8) NOT NULL,
 datacadastro DATE NOT NULL,
 cargo VARCHAR(30) NOT NULL,
 setor VARCHAR(30) NOT NULL
);

CREATE TABLE pessoa (
 nome VARCHAR(200) NOT NULL,
 nascimento DATE NOT NULL,
 responsavel VARCHAR(200) NOT NULL
);

CREATE TABLE chale (
 numero INT PRIMARY KEY,
 categoria VARCHAR(30) NOT NULL,
 diaria DOUBLE NOT NULL
 );  

CREATE TABLE reserva (
 numero INT PRIMARY KEY,
 cliente VARCHAR(50) NOT NULL,
 chale INT NULL,
 qtdAdulto INT NOT NULL,
 qtdCrianca INT NOT NULL,
 quantidade INT NOT NULL,
 dtIncio DATE NOT NULL,
 dtFim DATE NOT NULL, 
 desconto INT NOT NULL,
 estado VARCHAR(20) NOT NULL,
 dtCadastro DATE NOT NULL
 );

CREATE TABLE spa (
 -- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor FLOAT NOT NULL,
 servico VARCHAR(100) NOT NULL
 );

CREATE TABLE lanchonete(
 nome VARCHAR(100) NOT NULL,
 tipo VARCHAR(100) NOT NULL,  
-- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor FLOAT NOT NULL
 );

CREATE TABLE transporte(
 id VARCHAR(100) PRIMARY KEY,
 estado VARCHAR(30) NOT NULL,
 destino VARCHAR(200) NOT NULL,
 qtd INT NOT NULL,
 -- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor FLOAT NOT NULL
);

CREATE TABLE jacuzzi(
 qtd INT NOT NULL,
 -- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor FLOAT NOT NULL
);

CREATE TABLE internet(
 qtd INT NOT NULL,
 -- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor FLOAT NOT NULL
);