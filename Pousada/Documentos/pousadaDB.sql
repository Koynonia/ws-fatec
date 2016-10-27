/**
 * @author JESSICA CARNEIRO BATISTA
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 21/10/2016
 */

DROP DATABASE IF EXISTS pousada;

CREATE DATABASE pousada;

USE pousada;

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
 diaria DECIMAL(7,2) NOT NULL
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
 dtCadastro DATE NOT NULL
 );

CREATE TABLE spa (
 -- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor DECIMAL(7,2) NOT NULL,
 servico VARCHAR(100) NOT NULL
 );

CREATE TABLE lanchonete(
 nome VARCHAR(100) NOT NULL,
 tipo VARCHAR(100) NOT NULL,  
-- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor DECIMAL(7,2) NOT NULL
 );

CREATE TABLE transporte(
 id VARCHAR(100) PRIMARY KEY,
 estado VARCHAR(30) NOT NULL,
 destino VARCHAR(200) NOT NULL,
 qtd INT NOT NULL,
 -- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor DECIMAL(7,2) NOT NULL
);

CREATE TABLE jacuzzi(
 qtd INT NOT NULL,
 -- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor DECIMAL(7,2) NOT NULL
);

CREATE TABLE internet(
 qtd INT NOT NULL,
 -- cartao VARCHAR(100) NOT NULL,
 dt DATE NOT NULL,
 hora DATE NOT NULL,
 valor DECIMAL(7,2) NOT NULL
);

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
);

-- DADOS PARA TESTE:

INSERT INTO `chale` (`numero`, `categoria`, `diaria`) VALUES
(1, 'Luxo', '299.90'),
(2, 'Classic', '199.90'),
(3, 'Standard', '99.90');

INSERT INTO `cliente` (`nome`, `email`, `documento`, `docTipo`, 
`dtNasc`, `telefone`, `celular`, `endereco`, `bairro`, `cidade`, 
`estado`, `pais`, `cep`, `dtCadastro`, `ativo`) VALUES
('Fulano', 'fulano@email.com', '111.222.333-44', 'CPF', '1990-01-01', 
'1122223333', '1144445555', 'Rua do Fulano, 01', 'Jardim do Fulano', 
'São Paulo', 'São Paulo', 'Brasil', '01233444', '2016-10-23', 1),

('Sicrano', 'sicrano@email.com', '1112233FA', 'Passaporte', '1995-08-20', 
'12345678901', '12345678901', 'Rua do Sicrano, 01', 'Parque Sicrano', 
'São Paulo', 'São Paulo', 'Brasil', '01234567', '2016-10-20', 1),

('Beltrano da Silva', 'beltrano@email.com', '11222333', 'RG', '1995-04-23', 
'12345678900', '12345678902', 'Rua do Beltrano, 01', 'Parque Beltrano', 
'Salvador', 'Bahia', 'Brasil', '01234567', '2016-10-20', 1);

INSERT INTO `principal` (`principalInfo`, `principalDetalhe`, `chaleInfo`, 
`chaleDetalhe`, `lazerInfo`, `lazerDetalhe`, `servicoInfo`, `servicoDetalhe`, 
`reservaInfo`, `contatoInfo`, `versao`) VALUES

(
'INFO 1 - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 

'INFO 2 - Etiam volutpat purus leo, eget vehicula magna suscipit sed. Vivamus vitae diam id mauris pulvinar fringilla eu sed mauris. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque bibendum euismod elit ut aliquet. Aliquam a luctus urna, sit amet sodales ex. Etiam finibus urna magna, sed commodo nulla efficitur in. Sed volutpat dolor eros, quis commodo nulla pellentesque ac. Pellentesque laoreet, augue ac sodales iaculis, felis mi lobortis eros, sed pharetra diam nisl at odio. Quisque malesuada urna quis ligula blandit, sit amet placerat tellus suscipit. Donec varius lorem tempor, molestie sapien eu, dictum neque. Curabitur vitae purus vitae lectus fermentum consequat iaculis vel sem. Donec sed blandit risus. Curabitur tincidunt purus sit amet tellus suscipit volutpat.', 

'INFO 3 - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 

'INFO 4 - Etiam volutpat purus leo, eget vehicula magna suscipit sed. Vivamus vitae diam id mauris pulvinar fringilla eu sed mauris. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque bibendum euismod elit ut aliquet. Aliquam a luctus urna, sit amet sodales ex. Etiam finibus urna magna, sed commodo nulla efficitur in. Sed volutpat dolor eros, quis commodo nulla pellentesque ac. Pellentesque laoreet, augue ac sodales iaculis, felis mi lobortis eros, sed pharetra diam nisl at odio. Quisque malesuada urna quis ligula blandit, sit amet placerat tellus suscipit. Donec varius lorem tempor, molestie sapien eu, dictum neque. Curabitur vitae purus vitae lectus fermentum consequat iaculis vel sem. Donec sed blandit risus. Curabitur tincidunt purus sit amet tellus suscipit volutpat.', 

'INFO 5 - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 

'INFO 6 - Etiam volutpat purus leo, eget vehicula magna suscipit sed. Vivamus vitae diam id mauris pulvinar fringilla eu sed mauris. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque bibendum euismod elit ut aliquet. Aliquam a luctus urna, sit amet sodales ex. Etiam finibus urna magna, sed commodo nulla efficitur in. Sed volutpat dolor eros, quis commodo nulla pellentesque ac. Pellentesque laoreet, augue ac sodales iaculis, felis mi lobortis eros, sed pharetra diam nisl at odio. Quisque malesuada urna quis ligula blandit, sit amet placerat tellus suscipit. Donec varius lorem tempor, molestie sapien eu, dictum neque. Curabitur vitae purus vitae lectus fermentum consequat iaculis vel sem. Donec sed blandit risus. Curabitur tincidunt purus sit amet tellus suscipit volutpat.', 

'INFO 7 - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 
 
'INFO 8 - Etiam volutpat purus leo, eget vehicula magna suscipit sed. Vivamus vitae diam id mauris pulvinar fringilla eu sed mauris. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque bibendum euismod elit ut aliquet. Aliquam a luctus urna, sit amet sodales ex. Etiam finibus urna magna, sed commodo nulla efficitur in. Sed volutpat dolor eros, quis commodo nulla pellentesque ac. Pellentesque laoreet, augue ac sodales iaculis, felis mi lobortis eros, sed pharetra diam nisl at odio. Quisque malesuada urna quis ligula blandit, sit amet placerat tellus suscipit. Donec varius lorem tempor, molestie sapien eu, dictum neque. Curabitur vitae purus vitae lectus fermentum consequat iaculis vel sem. Donec sed blandit risus. Curabitur tincidunt purus sit amet tellus suscipit volutpat.', 

'INFO 9 - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 

'INFO 10 - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 

'0.1.5');