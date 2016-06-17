PROJETO REALIZAO POR

DENYS WYLLIAM SILVA & FERNANDO MORAES OLVIEIRA


Tabelas do Banco


```
#!sql


CREATE DATABASE bancoCondominio;

USE bancoCondominio;

CREATE TABLE morador(
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome CHAR(100) NOT NULL,
  telefone CHAR(11) UNIQUE
);

CREATE TABLE apartamento(
  id INT PRIMARY KEY AUTO_INCREMENT,
  numero INT(4) UNIQUE NOT NULL,
  quartos INT(1) NOT NULL,
  ocupacao CHAR(30) NOT NULL,
  id_morador INT NULL,
  FOREIGN KEY(id_morador) REFERENCES morador(id)
);

CREATE TABLE despesa_condominio(
  id INT PRIMARY KEY AUTO_INCREMENT,
  despesa CHAR(50) NOT NULL,
  valor FLOAT NOT NULL,
  dtVencimento DATE NOT NULL
);

CREATE TABLE despesa_apartamento(
  id INT PRIMARY KEY AUTO_INCREMENT,
  despesa CHAR(50) NOT NULL,
  valor DECIMAL(7, 2) NOT NULL,
  dtVencimento DATE NOT NULL,
  dtReferencia DATE NOT NULL,
  id_apartamento INT,
  FOREIGN KEY(id_apartamento) REFERENCES apartamento(id)
);

CREATE TABLE condominio_mensal(
  id INT PRIMARY KEY AUTO_INCREMENT,
  idApto INT NOT NULL,
  idDespesaApto INT,
  idDespesaCond INT, 
  multa FLOAT NOT NULL,
  valor FLOAT NOT NULL,
  dtVencimento DATE NOT NULL,
  dtPagamento DATE NOT NULL,
  dtProrrogado DATE, 
  FOREIGN KEY(idApto) REFERENCES apartamento(id),
  FOREIGN KEY(idDespesaApto) REFERENCES despesa_apartamento(id),
  FOREIGN KEY(idDespesaCond) REFERENCES despesa_condominio(id)
);

```