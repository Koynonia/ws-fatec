-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 24-Out-2016 às 18:10
-- Versão do servidor: 5.6.32
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pousada`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `chale`
--

CREATE TABLE `chale` (
  `numero` int(11) NOT NULL,
  `categoria` varchar(30) NOT NULL,
  `diaria` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `chale`
--

INSERT INTO `chale` (`numero`, `categoria`, `diaria`) VALUES
(1, 'Standard', 100),
(2, 'Master', 150),
(3, 'Luxo', 200);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `nome` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL,
  `documento` varchar(15) NOT NULL,
  `docTipo` varchar(15) DEFAULT NULL,
  `dtNasc` date NOT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  `celular` varchar(12) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `bairro` varchar(30) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `estado` varchar(15) NOT NULL,
  `pais` varchar(10) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `dtCadastro` date NOT NULL,
  `ativo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`nome`, `email`, `documento`, `docTipo`, `dtNasc`, `telefone`, `celular`, `endereco`, `bairro`, `cidade`, `estado`, `pais`, `cep`, `dtCadastro`, `ativo`) VALUES
('Fulano', 'fulano@email.com', '111.222.333-44', 'CPF', '1990-01-01', '1122223333', '1144445555', 'Rua do Fulano, 01', 'Jardim do Fulano', 'São Paulo', 'São Paulo', 'Brasil', '01233444', '2016-10-23', 1),
('Sicrano', 'sicrano@email.com', '1112233FA', 'Passaporte', '1995-08-20', '12345678901', '12345678901', 'Rua do Sicrano, 01', 'Parque Sicrano', 'São Paulo', 'São Paulo', 'Brasil', '01234567', '2016-10-20', 1),
('Beltrano da Silva', 'beltrano@email.com', '11222333', 'RG', '1995-04-23', '12345678900', '12345678902', 'Rua do Beltrano, 01', 'Parque Beltrano', 'Salvador', 'Bahia', 'Brasil', '01234567', '2016-10-20', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionario`
--

CREATE TABLE `funcionario` (
  `nome` varchar(100) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `nascimento` date NOT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  `celular` varchar(12) NOT NULL,
  `email` varchar(30) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  `bairro` varchar(30) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `estado` varchar(2) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `datacadastro` date NOT NULL,
  `cargo` varchar(30) NOT NULL,
  `setor` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `internet`
--

CREATE TABLE `internet` (
  `qtd` int(11) NOT NULL,
  `dt` date NOT NULL,
  `hora` date NOT NULL,
  `valor` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `jacuzzi`
--

CREATE TABLE `jacuzzi` (
  `qtd` int(11) NOT NULL,
  `dt` date NOT NULL,
  `hora` date NOT NULL,
  `valor` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `lanchonete`
--

CREATE TABLE `lanchonete` (
  `nome` varchar(100) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `dt` date NOT NULL,
  `hora` date NOT NULL,
  `valor` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

CREATE TABLE `pessoa` (
  `nome` varchar(200) NOT NULL,
  `nascimento` date NOT NULL,
  `responsavel` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `principal`
--

CREATE TABLE `principal` (
  `principalInfo` varchar(550) DEFAULT NULL,
  `principalDetalhe` varchar(850) DEFAULT NULL,
  `chaleInfo` varchar(550) DEFAULT NULL,
  `chaleDetalhe` varchar(850) DEFAULT NULL,
  `lazerInfo` varchar(550) DEFAULT NULL,
  `lazerDetalhe` varchar(850) DEFAULT NULL,
  `servicoInfo` varchar(550) DEFAULT NULL,
  `servicoDetalhe` varchar(850) DEFAULT NULL,
  `reservaInfo` varchar(550) DEFAULT NULL,
  `contatoInfo` varchar(550) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `principal`
--

INSERT INTO `principal` (`principalInfo`, `principalDetalhe`, `chaleInfo`, `chaleDetalhe`, `lazerInfo`, `lazerDetalhe`, `servicoInfo`, `servicoDetalhe`, `reservaInfo`, `contatoInfo`) VALUES
('INFO A - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 'INFO B - Etiam volutpat purus leo, eget vehicula magna suscipit sed. Vivamus vitae diam id mauris pulvinar fringilla eu sed mauris. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque bibendum euismod elit ut aliquet. Aliquam a luctus urna, sit amet sodales ex. Etiam finibus urna magna, sed commodo nulla efficitur in. Sed volutpat dolor eros, quis commodo nulla pellentesque ac. Pellentesque laoreet, augue ac sodales iaculis, felis mi lobortis eros, sed pharetra diam nisl at odio. Quisque malesuada urna quis ligula blandit, sit amet placerat tellus suscipit. Donec varius lorem tempor, molestie sapien eu, dictum neque. Curabitur vitae purus vitae lectus fermentum consequat iaculis vel sem. Donec sed blandit risus. Curabitur tincidunt purus sit amet tellus suscipit volutpat.', 'INFO C - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 'INFO D - Etiam volutpat purus leo, eget vehicula magna suscipit sed. Vivamus vitae diam id mauris pulvinar fringilla eu sed mauris. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque bibendum euismod elit ut aliquet. Aliquam a luctus urna, sit amet sodales ex. Etiam finibus urna magna, sed commodo nulla efficitur in. Sed volutpat dolor eros, quis commodo nulla pellentesque ac. Pellentesque laoreet, augue ac sodales iaculis, felis mi lobortis eros, sed pharetra diam nisl at odio. Quisque malesuada urna quis ligula blandit, sit amet placerat tellus suscipit. Donec varius lorem tempor, molestie sapien eu, dictum neque. Curabitur vitae purus vitae lectus fermentum consequat iaculis vel sem. Donec sed blandit risus. Curabitur tincidunt purus sit amet tellus suscipit volutpat.', 'INFO E - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 'INFO F - Etiam volutpat purus leo, eget vehicula magna suscipit sed. Vivamus vitae diam id mauris pulvinar fringilla eu sed mauris. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque bibendum euismod elit ut aliquet. Aliquam a luctus urna, sit amet sodales ex. Etiam finibus urna magna, sed commodo nulla efficitur in. Sed volutpat dolor eros, quis commodo nulla pellentesque ac. Pellentesque laoreet, augue ac sodales iaculis, felis mi lobortis eros, sed pharetra diam nisl at odio. Quisque malesuada urna quis ligula blandit, sit amet placerat tellus suscipit. Donec varius lorem tempor, molestie sapien eu, dictum neque. Curabitur vitae purus vitae lectus fermentum consequat iaculis vel sem. Donec sed blandit risus. Curabitur tincidunt purus sit amet tellus suscipit volutpat.', 'INFO G - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 'INFO H - Etiam volutpat purus leo, eget vehicula magna suscipit sed. Vivamus vitae diam id mauris pulvinar fringilla eu sed mauris. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Quisque bibendum euismod elit ut aliquet. Aliquam a luctus urna, sit amet sodales ex. Etiam finibus urna magna, sed commodo nulla efficitur in. Sed volutpat dolor eros, quis commodo nulla pellentesque ac. Pellentesque laoreet, augue ac sodales iaculis, felis mi lobortis eros, sed pharetra diam nisl at odio. Quisque malesuada urna quis ligula blandit, sit amet placerat tellus suscipit. Donec varius lorem tempor, molestie sapien eu, dictum neque. Curabitur vitae purus vitae lectus fermentum consequat iaculis vel sem. Donec sed blandit risus. Curabitur tincidunt purus sit amet tellus suscipit volutpat.', 'INFO I - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.', 'INFO J - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec efficitur erat et diam tincidunt facilisis. Aenean ut nulla ac ipsum ullamcorper porttitor. Morbi et pulvinar enim. Mauris bibendum erat neque, non interdum est consectetur non. Sed facilisis enim et sem venenatis, vel mattis felis aliquet. Nullam non dictum lacus. Duis orci nibh, scelerisque non volutpat non, dignissim id ex. Aenean blandit nunc ut arcu tincidunt semper. Phasellus bibendum leo dignissim nunc lacinia commodo. Aliquam id urna eu sapien tempus scelerisque.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `reserva`
--

CREATE TABLE `reserva` (
  `numero` int(11) NOT NULL,
  `qtdpessoas` int(11) NOT NULL,
  `datainicio` date NOT NULL,
  `datafim` date NOT NULL,
  `estado` varchar(30) NOT NULL,
  `statuspagamento` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `spa`
--

CREATE TABLE `spa` (
  `dt` date NOT NULL,
  `hora` date NOT NULL,
  `valor` float NOT NULL,
  `servico` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `transporte`
--

CREATE TABLE `transporte` (
  `id` varchar(100) NOT NULL,
  `estado` varchar(30) NOT NULL,
  `destino` varchar(200) NOT NULL,
  `qtd` int(11) NOT NULL,
  `dt` date NOT NULL,
  `hora` date NOT NULL,
  `valor` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chale`
--
ALTER TABLE `chale`
  ADD PRIMARY KEY (`numero`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`documento`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `celular` (`celular`),
  ADD UNIQUE KEY `telefone` (`telefone`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`cpf`),
  ADD UNIQUE KEY `celular` (`celular`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `telefone` (`telefone`);

--
-- Indexes for table `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`numero`);

--
-- Indexes for table `transporte`
--
ALTER TABLE `transporte`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
