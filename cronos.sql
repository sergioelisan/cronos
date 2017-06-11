create database cr00n0s;
use cr00n0s;

-- OK
create table docente (
	id INT NOT NULL AUTO_INCREMENT,
	matricula INT NOT NULL, 
	nome VARCHAR(128) NOT NULL, 
	formacao INT NOT NULL, 
	contratacao DATETIME NOT NULL, 
	nucleo INT NOT NULL, 
	score INT NOT NULL, 
	primeiroturno INT NOT NULL, 
	segundoturno INT NOT NULL,
	PRIMARY KEY(id)
);

-- OK
create table ocupacao (
	matricula INT NOT NULL, 
	turno INT NOT NULL, 
	dia DATETIME NOT NULL, 
	disciplina1 INT NOT NULL, 
	docente1 INT NOT NULL, 
	laboratorio1 INT NOT NULL, 
	disciplina2 INT NOT NULL, 
	docente2 INT NOT NULL, 
	laboratorio2 INT NOT NULL
);

-- OK
create table proficiencia (
	id INT NOT NULL AUTO_INCREMENT,
	matricula INT NOT NULL, 
	disciplina INT NOT NULL, 
	nivel INT NOT NULL, 
	scoretemp INT NOT NULL,
	PRIMARY KEY(id)
);

-- OK
create table feriados (
	id INT NOT NULL AUTO_INCREMENT,
	dia DATETIME NOT NULL, 
	descricao VARCHAR(128) NOT NULL,
	PRIMARY KEY(id)
);

create table horario (
	turma INT NOT NULL, 
	dia DATETIME NOT NULL, 
	disciplina1 INT NOT NULL, 
	docente1 INT NOT NULL, 
	laboratorio1 INT NOT NULL, 
	disciplina2 INT NOT NULL, 
	docente2 INT NOT NULL, 
	laboratorio2 INT NOT NULL
);

-- OK
create table laboratorio (
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(128) NOT NULL, 
	descricao VARCHAR(128) NOT NULL,
	PRIMARY KEY(id)
);

-- OK
create table nucleo (
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(128) NOT NULL,
	PRIMARY KEY(id)
);

-- OK
create table turma (
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(128) NOT NULL, 
	nucleo INT NOT NULL, 
	entrada DATETIME NOT NULL, 
	saida DATETIME NOT NULL, 
	turno INT NOT NULL, 
	habilitacao INT NOT NULL,
	PRIMARY KEY(id)
);

-- OK
create table disciplina (
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(128) NOT NULL, 
	nucleo INT NOT NULL, 
	modulo INT NOT NULL, 
	ementa VARCHAR(128) NOT NULL, 
	laboratorio INT NOT NULL, 
	carga INT NOT NULL,
	PRIMARY KEY(id)
);
