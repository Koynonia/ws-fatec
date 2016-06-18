PROJETO REALIZAO POR

DENYS WYLLIAM SILVA & FERNANDO MORAES OLVIEIRA

A documentação deste sistema se encontra na pasta Documentation juntamente com 
as tabelas do banco de dados (arquivo bancocondominio.sql).

Obs.: Para a conexão no banco, trocar o usuário e senha do MySQL 
na Classe GenericDao (package persistence).

A documentação deste sistema se encontra na pasta Documentation juntamente com 
as tabelas do banco de dados (arquivo bancocondominio.sql).


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
  valor FLOAT NOT NULL,
  dtVencimento DATE NOT NULL,
  dtPagamento DATE NOT NULL,
  dtProrrogado DATE, 
  FOREIGN KEY(idApto) REFERENCES apartamento(id) 
);

```
=======
Obs.: Para a conexão no banco, trocar o usuário e senha do MySQL 
na Classe GenericDao (package persistence).
