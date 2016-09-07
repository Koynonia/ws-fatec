Projeto de controle de uma Pousada para a Matéria Engenharia de Software 2 
do curso de Análise e Desenvolvimento de Sistemas da FATEC ZL. 
Tarde - 2º Semestre 2016

PROJETO REALIZAO POR:

- EMERSON SANTOS BRITO
- FERNANDO MORAES OLVIEIRA
- GUILHERME JATOBÁ
- JESSICA CARNEIRO BATISTA
- WELLINGTON BRENO SANTOS CRUZ

A documentação deste sistema se encontra na pasta "Documentos" juntamente 
com as tabelas do banco de dados (pousada.sql).

OBS.: Para a conexão no banco, trocar o usuário e senha do MySQL 
na Classe GenericDao (package persistence).



```
#!java

CREATE DATABASE pousada;

USE pousada;

CREATE TABLE cliente(
  nome CHAR(30) NOT NULL,
  cpf CHAR(11) PRIMARY KEY,
  endereco CHAR(100) NOT NULL,
  bairro CHAR(100) NOT NULL,
  cidade CHAR(100) NOT NULL,
  telefone CHAR(11) UNIQUE NOT NULL,
  celular CHAR(12) UNIQUE,
  email CHAR(20) UNIQUE NOT NULL
);
```