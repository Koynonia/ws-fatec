-- phpMyAdmin SQL Dump
-- version 4.6.1
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: 16-Jun-2016 às 14:33
-- Versão do servidor: 5.6.30
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bancocondominio`
--

CREATE DATABASE bancoCondominio;

USE bancoCondominio;

-- --------------------------------------------------------

--
-- Estrutura da tabela `apartamento`
--

CREATE TABLE `apartamento` (
  `id` int(11) NOT NULL,
  `numero` int(4) NOT NULL,
  `quartos` int(1) NOT NULL,
  `ocupacao` char(30) NOT NULL,
  `id_morador` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `apartamento`
--

INSERT INTO `apartamento` (`id`, `numero`, `quartos`, `ocupacao`, `id_morador`) VALUES
(3, 95, 2, 'Proprietario', 1),
(4, 25, 3, 'Inquilino', 2),
(5, 2, 3, 'Vazio', 3),
(9, 20, 3, 'Proprietario', 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `condominio_mensal`
--

CREATE TABLE `condominio_mensal` (
  `id` int(11) NOT NULL,
  `idApto` int(11) NOT NULL,
  `idDespesaApto` int(11) NOT NULL,
  `idDespesaCond` int(11) NOT NULL,
  `multa` float NOT NULL,
  `valor` float NOT NULL,
  `dtVencimento` date NOT NULL,
  `dtPagamento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `despesa_apartamento`
--

CREATE TABLE `despesa_apartamento` (
  `id` int(11) NOT NULL,
  `despesa` char(50) NOT NULL,
  `valor` decimal(7,2) NOT NULL,
  `dtVencimento` date NOT NULL,
  `dtReferencia` date NOT NULL,
  `id_apartamento` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `despesa_apartamento`
--

INSERT INTO `despesa_apartamento` (`id`, `despesa`, `valor`, `dtVencimento`, `dtReferencia`, `id_apartamento`) VALUES
(1, 'Salão de Festa', '80.00', '2016-06-10', '2016-06-01', 3),
(2, 'Salão de Festa', '90.00', '2016-07-10', '2016-07-01', 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `despesa_condominio`
--

CREATE TABLE `despesa_condominio` (
  `id` int(11) NOT NULL,
  `despesa` char(30) NOT NULL,
  `valor` float NOT NULL,
  `dtVencimento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `despesa_condominio`
--

INSERT INTO `despesa_condominio` (`id`, `despesa`, `valor`, `dtVencimento`) VALUES
(1, 'Conta de Luz', 443.32, '2016-06-20'),
(2, 'Manutenção do Portão', 345, '2016-08-20'),
(3, 'Confecção de Chaves', 40.5, '2016-07-30'),
(4, 'Conserto Hidráulico', 100, '2016-05-10'),
(5, 'Cópia de Documento', 45.9, '2016-06-30'),
(6, 'Conserto de Câmera', 30, '2016-05-15'),
(7, 'Cópia de Documento', 10, '2016-07-15'),
(23, 'Folha Salarial', 4000, '2016-06-10'),
(24, 'Pintura Garagem', 100, '2016-08-20'),
(25, 'Conserto do Registro', 450, '2016-08-15'),
(26, 'Compra de Lixeiras', 1000, '2016-06-30'),
(28, 'Folha Salarial', 4500, '2016-07-10'),
(29, 'Conta de Luz', 550, '2016-07-20'),
(30, 'Conta de Luz', 490, '2016-08-20'),
(31, 'Conta de Luz', 480, '2016-04-20');

-- --------------------------------------------------------

--
-- Estrutura da tabela `morador`
--

CREATE TABLE `morador` (
  `id` int(11) NOT NULL,
  `nome` char(100) NOT NULL,
  `telefone` char(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `morador`
--

INSERT INTO `morador` (`id`, `nome`, `telefone`) VALUES
(1, 'Fernando', '1122223333'),
(2, 'Denys', '1133334444'),
(3, 'Moderador', '0000'),
(4, 'Cristina', '115556666');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apartamento`
--
ALTER TABLE `apartamento`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numero` (`numero`),
  ADD KEY `id_morador` (`id_morador`);

--
-- Indexes for table `condominio_mensal`
--
ALTER TABLE `condominio_mensal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idApto` (`idApto`),
  ADD KEY `idDespesaApto` (`idDespesaApto`),
  ADD KEY `idDespesaCond` (`idDespesaCond`);

--
-- Indexes for table `despesa_apartamento`
--
ALTER TABLE `despesa_apartamento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_apartamento` (`id_apartamento`);

--
-- Indexes for table `despesa_condominio`
--
ALTER TABLE `despesa_condominio`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `morador`
--
ALTER TABLE `morador`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `telefone` (`telefone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `apartamento`
--
ALTER TABLE `apartamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `condominio_mensal`
--
ALTER TABLE `condominio_mensal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `despesa_apartamento`
--
ALTER TABLE `despesa_apartamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `despesa_condominio`
--
ALTER TABLE `despesa_condominio`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `morador`
--
ALTER TABLE `morador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `apartamento`
--
ALTER TABLE `apartamento`
  ADD CONSTRAINT `apartamento_ibfk_1` FOREIGN KEY (`id_morador`) REFERENCES `morador` (`id`);

--
-- Limitadores para a tabela `condominio_mensal`
--
ALTER TABLE `condominio_mensal`
  ADD CONSTRAINT `condominio_mensal_ibfk_1` FOREIGN KEY (`idApto`) REFERENCES `apartamento` (`id`),
  ADD CONSTRAINT `condominio_mensal_ibfk_2` FOREIGN KEY (`idDespesaApto`) REFERENCES `despesa_apartamento` (`id`),
  ADD CONSTRAINT `condominio_mensal_ibfk_3` FOREIGN KEY (`idDespesaCond`) REFERENCES `despesa_condominio` (`id`);

--
-- Limitadores para a tabela `despesa_apartamento`
--
ALTER TABLE `despesa_apartamento`
  ADD CONSTRAINT `despesa_apartamento_ibfk_1` FOREIGN KEY (`id_apartamento`) REFERENCES `apartamento` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
