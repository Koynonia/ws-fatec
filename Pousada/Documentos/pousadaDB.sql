/**
 * @author JESSICA CARNEIRO BATISTA
 * Matéria Engenharia de Software 2
 * FATEC ZL 5º ADS - Tarde
 * 21/10/2016
 */

DROP DATABASE IF EXISTS pousada;

CREATE DATABASE pousada;

USE pousada;

CREATE TABLE principal(
id INT AUTO_INCREMENT PRIMARY KEY,
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
) ENGINE = innodb;

CREATE TABLE reserva (
id INT AUTO_INCREMENT PRIMARY KEY,
cliente INT NOT NULL,
chale INT NOT NULL,
qtdAdulto INT NOT NULL,
qtdCrianca INT NOT NULL,
dtInicio DATE NOT NULL,
dtFim DATE NOT NULL, 
mensagem VARCHAR (300),
desconto INT NOT NULL,
dtCadastro DATE NOT NULL
) ENGINE = innodb;

CREATE TABLE contato(
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
email VARCHAR(30) NOT NULL,
telefone VARCHAR(11) NOT NULL,
cidade VARCHAR(30),
estado VARCHAR(15),
pais VARCHAR(10),
assunto INT NOT NULL,
mensagem VARCHAR (300),
dtCadastro DATE NOT NULL
) ENGINE = innodb;

CREATE TABLE cliente(
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
email VARCHAR(30),
documento VARCHAR(15) UNIQUE NOT NULL,
docTipo VARCHAR(15),
dtNasc DATE,
telefone VARCHAR(11),
celular VARCHAR(12),
endereco VARCHAR(200),
bairro VARCHAR(30),
cidade VARCHAR(30),
estado VARCHAR(15),
pais VARCHAR(10),
cep VARCHAR(8),
ativo BOOLEAN NOT NULL,
dtCadastro DATE NOT NULL
) ENGINE = innodb;

CREATE TABLE funcionario (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
cpf VARCHAR(11) UNIQUE NOT NULL,
dtNasc DATE NOT NULL,
telefone VARCHAR(11) UNIQUE,
celular VARCHAR(12) NOT NULL,
email VARCHAR(30) UNIQUE NOT NULL,
endereco VARCHAR(200) NOT NULL,
bairro VARCHAR(30) NOT NULL,
cidade VARCHAR(30) NOT NULL,
estado VARCHAR(2) NOT NULL,
cep VARCHAR(8) NOT NULL,
cargo VARCHAR(30) NOT NULL,
setor VARCHAR(30) NOT NULL,
ativo BOOLEAN NOT NULL,
datacadastro DATE NOT NULL
) ENGINE = innodb;

CREATE TABLE pessoa (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(200) NOT NULL,
dtNasc DATE NOT NULL,
responsavel VARCHAR(200) NOT NULL
) ENGINE = innodb;

CREATE TABLE chale (
id INT AUTO_INCREMENT PRIMARY KEY,
categoria VARCHAR(30) NOT NULL,
diaria DECIMAL(7,2) NOT NULL
) ENGINE = innodb;

CREATE TABLE spa (
id INT AUTO_INCREMENT PRIMARY KEY,
servico VARCHAR(50) NOT NULL,
dtReserva DATE NOT NULL,
hrReserva DATE NOT NULL,
valor DECIMAL(7,2) NOT NULL
) ENGINE = innodb;

CREATE TABLE lanchonete(
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
tipo VARCHAR(100) NOT NULL,
dtReserva DATE NOT NULL,
hrReserva DATE NOT NULL,
valor DECIMAL(7,2) NOT NULL
) ENGINE = innodb;

CREATE TABLE transporte(
id INT AUTO_INCREMENT PRIMARY KEY,
placa VARCHAR(100) UNIQUE NOT NULL,
estado VARCHAR(30) NOT NULL,
destino VARCHAR(200) NOT NULL,
dtReserva DATE NOT NULL,
hrReserva DATE NOT NULL,
valor DECIMAL(7,2) NOT NULL
) ENGINE = innodb;

CREATE TABLE jacuzzi(
id INT AUTO_INCREMENT PRIMARY KEY,
dtReserva DATE NOT NULL,
hrReserva DATE NOT NULL,
valor DECIMAL(7,2) NOT NULL
) ENGINE = innodb;

CREATE TABLE internet(
id INT AUTO_INCREMENT PRIMARY KEY,
dtReserva DATE NOT NULL,
hrReserva DATE NOT NULL,
valor DECIMAL(7,2) NOT NULL
) ENGINE = innodb;


-- INSERE AS FK's NAS TABELAS

ALTER TABLE `reserva` ADD CONSTRAINT `fk_cliente` FOREIGN KEY ( `cliente` ) REFERENCES `cliente` ( `id` );
ALTER TABLE `reserva` ADD CONSTRAINT `fk_chale` FOREIGN KEY ( `chale` ) REFERENCES `chale` ( `id` );

-- DADOS PARA TESTE:

INSERT INTO `chale` (`id`, `categoria`, `diaria`) VALUES
(1, 'Luxo', '299.90'),
(2, 'Clássico', '199.90'),
(3, 'Padrão', '99.90'),
(4, 'Luxo', '299.90');

INSERT INTO `cliente` (`id`, `nome`, `email`, `documento`, `docTipo`, 
`dtNasc`, `telefone`, `celular`, `endereco`, `bairro`, `cidade`, 
`estado`, `pais`, `cep`, `dtCadastro`, `ativo`) VALUES
(1,'Fulano', 'fulano@email.com', '111.222.333-44', 'CPF', '1990-01-01', 
'1122223333', '1144445555', 'Rua do Fulano, 01', 'Jardim do Fulano', 
'São Paulo', 'São Paulo', 'Brasil', '01233444', '2016-10-23', 1),

(2,'Sicrano', 'sicrano@email.com', '1112233FA', 'Passaporte', '1995-08-20', 
'12345678901', '12345678901', 'Rua do Sicrano, 01', 'Parque Sicrano', 
'São Paulo', 'São Paulo', 'Brasil', '01234567', '2016-10-20', 0),

(3,'Beltrano da Silva', 'beltrano@email.com', '11222333', 'RG', '1995-04-23', 
'12345678900', '12345678902', 'Rua do Beltrano, 01', 'Parque Beltrano', 
'Salvador', 'Bahia', 'Brasil', '01234567', '2016-10-20', 0);

INSERT INTO `reserva` (`id`, `cliente`, `chale`, `qtdAdulto`, `qtdCrianca`, 
`dtInicio`, `dtFim`, `mensagem`, `desconto`, `dtCadastro`) VALUES
(86, 2, 4, 1, 0, '2016-11-01', '2016-11-05', '', 0, '2016-10-30'),
(87, 3, 1, 1, 0, '2016-11-01', '2016-11-01', 'f', 0, '2016-11-01'),
(88, 3, 3, 1, 0, '2016-11-10', '2016-11-15', '', 0, '2016-11-01');

INSERT INTO `principal` (`id`, `principalInfo`, `principalDetalhe`, `chaleInfo`, 
`chaleDetalhe`, `lazerInfo`, `lazerDetalhe`, `servicoInfo`, `servicoDetalhe`, 
`reservaInfo`, `contatoInfo`, `versao`) VALUES

(1,
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

'0.1.5'
);