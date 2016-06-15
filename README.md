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

create table despesa_apartamento(
	id int primary key auto_increment,
	despesa char(50) not null,
	valor decimal(7,2) not null,
	dtVencimento DATE NOT NULL,
	dtReferencia DATE NOT NULL,
	id_apartamento int,
	foreign key (id_apartamento) references apartamento(id)
	);

```