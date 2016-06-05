Tabelas do Banco

create table apartamento(
 	id int primary key auto_increment,
	numero int(4) unique not null,
	quartos int(1) not null,
	ocupacao char(30) not null
);


create table morador(
	id int primary key auto_increment,
	nome char(100) not null,
	telefone char(11) unique
);

Alter table apartamento
ADD CONSTRAINT id_morador
foreign key (id) 
references morador(id);