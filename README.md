Mantenha abaixo as tabelas necessárias para o funcionamento do seu código.

Tabelas do Banco


```
#!sql

create table apartamento(
 	id int primary key auto_increment,
	numero int(4) unique not null,
	quartos int(1) not null,
	ocupacao char(30) not null,
	id_morador int null,
	foreign key (id_morador) references morador(id)
);


create table morador(
	id int primary key auto_increment,
	nome char(100) not null,
	telefone char(11) unique
);
```