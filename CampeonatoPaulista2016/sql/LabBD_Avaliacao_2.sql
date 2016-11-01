/*
* Laboratório de Banco de Dados
* Prof. M.Sc. Leandro Colevati dos Santos
* 
* AVALIAÇÃO 1: "Campeonato Paulista 2016"
*/

/*
* Criação da Base de Dados "Campeonato"
*/

IF (OBJECT_ID('Campeonato') IS NOT NULL)
    DROP DATABASE Campeonato
GO

CREATE DATABASE Campeonato
GO
USE Campeonato

/*
* Criação da Tabela "Times" e preenchimento com os registros de 20 times 
*/

IF (OBJECT_ID('Times') IS NOT NULL)
  DROP TABLE Times
GO

CREATE TABLE Times(
CodigoTime INT IDENTITY NOT NULL, 
NomeTime VARCHAR(100) NOT NULL, 
Cidade VARCHAR(50) NOT NULL,
Estadio VARCHAR(30) NOT NULL
PRIMARY KEY(CodigoTime))

INSERT INTO Times VALUES 
('Esporte Clube Água Santa', 'Diadema', 'Distrital do Inamar'), 
('Grêmio Osasco Audax', 'Osasco', 'José Liberatti'), 
('Botafogo Futebol Clube', 'Ribeirão Preto', 'Santa Cruz'), 
('Capivariano Futebol Clube', 'Capivari', 'Arena Capivari'), 
('Sport Club Corinthians Paulista', 'São Paulo', 'Arena Corinthians'), 
('Associação Ferroviária de Esportes', 'Araraquara', 'Fonte Luminosa'), 
('Ituano Futebol Clube', 'Itu', 'Novelli Júnior'), 
('Clube Atlético Linense', 'Lins', 'Gilberto Siqueira Lopes'), 
('Mogi Mirim Esporte Clube', 'Mogi Mirim', 'Vail Chaves'), 
('Grêmio Novorizontino', 'Novo Horizonte', 'Jorge Ismael de Biasi'), 
('Oeste Futebol Clube', 'Itápolis', 'Amaros'), 
('Sociedade Esportiva Palmeiras', 'São Paulo', 'Allianz Parque'), 
('Associação Atlética Ponte Preta', 'Campinas', 'Moisés Lucarelli'), 
('Red Bull Brasil', 'Campinas', 'Moisés Lucarelli'), 
('Rio Claro Futebol Clube', 'Rio Claro', 'Augusto Schmidt Filho'), 
('Santos Futebol Clube', 'Santos', 'Vila Belmiro'), 
('Esporte Clube São Bento', 'Sorocaba', 'Walter Ribeiro'), 
('São Bernardo Futebol Clube', 'São Bernardo do Campo', 'Primeiro de Maio'), 
('São Paulo Futebol Clube', 'São Paulo', 'Morumbi'), 
('Esporte Clube XV de Novembro', 'Piracicaba', 'Barão de Serra Negra')

/*
* Criação da Tabela "Grupos" 
*/

IF (OBJECT_ID('Grupos') IS NOT NULL)
  DROP TABLE Grupos
GO

CREATE TABLE Grupos(
Grupo CHAR(1), 
CodigoTime INT NOT NULL 
FOREIGN KEY (CodigoTime) 
REFERENCES Times(CodigoTime))

/*
* Criação da Tabela "Jogos" 
*/

IF (OBJECT_ID('Jogos') IS NOT NULL)
  DROP TABLE Jogos
GO

CREATE TABLE Jogos(
CodigoTimeA INT, 
CodigoTimeB INT, 
GolsTimeA INT, 
GolsTimeB INT, 
Data DATE
FOREIGN KEY (CodigoTimeA) 
REFERENCES Times(CodigoTime), 
FOREIGN KEY (CodigoTimeB) 
REFERENCES Times(CodigoTime))


/*
* 1) Procedure que divide os times nos quatro grupos, preenchendo, aleatoriamente 
* (exceção da regra: Coritnthians, Palmeiras, Santos e São Paulo NÃO PODEM estar no mesmo grupo).
*/

IF (OBJECT_ID('sp_grupos') IS NOT NULL)
  DROP PROCEDURE sp_grupos
GO

CREATE PROCEDURE sp_grupos (@trigger INT)
AS DECLARE 	@numTimes INT, 
			@numGrupos INT, 
			@numTimesPorGrupo INT, 
			@contador INT,
			@grupo CHAR,
			@sqlTimes VARCHAR(MAX),
			@sqlGrupo VARCHAR(MAX)
			
IF @trigger = 0
BEGIN
	PRINT 'Passou 0'
	EXEC ('ALTER TABLE Grupos DISABLE TRIGGER ALL')
END
ELSE IF @trigger = 1
BEGIN
	PRINT 'Passou 1'
	EXEC ('ALTER TABLE Grupos ENABLE TRIGGER ALL')
END

DELETE FROM Grupos
SELECT @numTimes = COUNT(CodigoTime) FROM Times
SET @numGrupos = 4
SET @numTimesPorGrupo = @numTimes / @numGrupos

SET @contador = 1;
SET @sqlTimes = 'INSERT INTO Grupos(CodigoTime)
							SELECT TOP 1 t.CodigoTime 
							FROM Times AS t 
							LEFT JOIN Grupos AS g 
								ON t.CodigoTime = g.CodigoTime
								WHERE t.nomeTime NOT LIKE ''%Corinthians%'' AND 
									t.nomeTime NOT LIKE ''%Palmeiras%'' AND 
									t.nomeTime NOT LIKE ''%Santos%'' AND 
									t.nomeTime NOT LIKE ''%São Paulo%'' AND
									g.CodigoTime IS NULL 
								ORDER BY NEWID()'

WHILE @contador <= @numTimes - @numGrupos 
BEGIN
	SET @grupo = NULL
	IF @contador <= 4
	BEGIN
		SET @grupo = 'A'
		EXEC(@sqlTimes)
		SET @sqlGrupo = 'UPDATE Grupos SET Grupo = ''' + @grupo + ''' WHERE Grupo IS NULL'
		EXEC (@sqlGrupo)
	END
	ELSE
	BEGIN
		IF @contador > 4 AND @contador <= 8
		BEGIN
			SET @grupo = 'B'
			EXEC(@sqlTimes)
			SET @sqlGrupo = 'UPDATE Grupos SET Grupo = ''' + @grupo + ''' WHERE Grupo IS NULL'
			EXEC (@sqlGrupo)
		END
		ELSE
		BEGIN
			IF @contador > 8 AND @contador <= 12
			BEGIN
				SET @grupo = 'C'
				EXEC(@sqlTimes)
				SET @sqlGrupo = 'UPDATE Grupos SET Grupo = ''' + @grupo + ''' WHERE Grupo IS NULL'
				EXEC (@sqlGrupo)
			END
			ELSE
			BEGIN
				IF @contador > 12
				BEGIN
					SET @grupo = 'D'
					EXEC(@sqlTimes)
					SET @sqlGrupo = 'UPDATE Grupos SET Grupo = ''' + @grupo + ''' WHERE Grupo IS NULL'
					EXEC (@sqlGrupo)
				END
			END
		END
	END
	SET @contador = @contador + 1
END

SET @contador = 1;
SET @sqlTimes = 'INSERT INTO Grupos(CodigoTime)
							SELECT TOP 1 t.CodigoTime 
							FROM Times AS t 
							LEFT JOIN Grupos AS g 
								ON t.CodigoTime = g.CodigoTime
								WHERE t.nomeTime LIKE ''%Corinthians%'' AND 
									t.nomeTime LIKE ''%Palmeiras%'' AND 
									t.nomeTime LIKE ''%Santos%'' AND 
									t.nomeTime LIKE ''%São Paulo%'' OR
									g.CodigoTime IS NULL 
								ORDER BY NEWID()'
WHILE @contador <= 4 
BEGIN
	SET @grupo = NULL
	IF @contador = 1
	BEGIN
		SET @grupo = 'A'
		EXEC(@sqlTimes)
		SET @sqlGrupo = 'UPDATE Grupos SET Grupo = ''' + @grupo + ''' WHERE Grupo IS NULL'
		EXEC (@sqlGrupo)
	END
	ELSE
	BEGIN
		IF @contador = 2
		BEGIN
			SET @grupo = 'B'
			EXEC(@sqlTimes)
			SET @sqlGrupo = 'UPDATE Grupos SET Grupo = ''' + @grupo + ''' WHERE Grupo IS NULL'
			EXEC (@sqlGrupo)
		END
		ELSE
		BEGIN
			IF @contador = 3
			BEGIN
				SET @grupo = 'C'
				EXEC(@sqlTimes)
				SET @sqlGrupo = 'UPDATE Grupos SET Grupo = ''' + @grupo + ''' WHERE Grupo IS NULL'
				EXEC (@sqlGrupo)
			END
			ELSE
			BEGIN
				IF @contador = 4
				BEGIN
					SET @grupo = 'D'
					EXEC(@sqlTimes)
					SET @sqlGrupo = 'UPDATE Grupos SET Grupo = ''' + @grupo + ''' WHERE Grupo IS NULL'
					EXEC (@sqlGrupo)
				END
			END
		END
	END
	SET @contador = @contador + 1
END

/*
* TESTE

IF (OBJECT_ID('sp_grupos') IS NOT NULL)
	EXEC sp_grupos '1'
	SELECT * FROM Grupos
GO

*/


/*
* 2) Procedure que gera as rodadas dos jogos: 
* A primeira fase ocorrerá em 15 datas seguidas, 
* sempre rodada cheia (os 10 jogos com todos os 20 times), 
* aos domingos e quartas
*/

IF (OBJECT_ID('sp_jogos') IS NOT NULL)
  DROP PROCEDURE sp_jogos
GO

CREATE PROCEDURE sp_jogos (@dtInicio SMALLDATETIME, @trigger INT)
AS DECLARE 	@numTimes INT,
			@rodadas INT,
			@timesRodada INT,
			@dtFinal SMALLDATETIME, 
			@sqlDomFaseA VARCHAR(MAX),
			@sqlQuaFaseA VARCHAR(MAX),
			@sqlDomFaseB VARCHAR(MAX),
			@sqlQuaFaseB VARCHAR(MAX),
			@sqlDomFaseC VARCHAR(MAX),
			@sqlQuaFaseC VARCHAR(MAX),
			@sqlData SMALLDATETIME
			
IF @trigger = 0
BEGIN
	PRINT 'Passou 0'
	EXEC ('ALTER TABLE Jogos DISABLE TRIGGER ALL')
END
ELSE IF @trigger = 1
BEGIN
	PRINT 'Passou 1'
	EXEC ('ALTER TABLE Jogos ENABLE TRIGGER ALL')
END

DELETE FROM Jogos			
SET @rodadas = 15
SELECT @numTimes = COUNT(CodigoTime) FROM Times

SET @sqlDomFaseA = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''A'') a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''B''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'


SET @sqlQuaFaseA = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''C''
								ORDER BY NEWID()) a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''D''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'

SET @sqlDomFaseB = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''A'') a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''D''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'

SET @sqlQuaFaseB = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''B''
								ORDER BY NEWID()) a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''C''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'

SET @sqlDomFaseC = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''A'') a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''C''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'

SET @sqlQuaFaseC = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''B''
								ORDER BY NEWID()) a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								FULL OUTER JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''D''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'

SET @dtFinal = DATEADD(Day, @rodadas * 2.5, @dtInicio)
WHILE @dtInicio <= @dtFinal
BEGIN
	SET @timesRodada = 1
	WHILE @timesRodada <= @numTimes/4
	BEGIN
		IF (DATEPART( DW,@dtInicio )) = 1
		BEGIN
			EXEC(@sqlDomFaseA)
			IF @timesRodada = @numTimes/4
			BEGIN
				UPDATE Jogos SET Data = @dtInicio WHERE Data IS NULL;	
			END
		END
		ELSE
		BEGIN
			IF (DATEPART( DW,@dtInicio )) = 4
			BEGIN
				EXEC(@sqlQuaFaseA)
				IF @timesRodada = @numTimes/4
				BEGIN
					UPDATE Jogos SET Data = @dtInicio WHERE Data IS NULL;	
				END
			END
		END
		SET @timesRodada = @timesRodada + 1
	END
	SET @dtInicio = DATEADD(Day, 1, @dtInicio)
END

SET @dtFinal = DATEADD(Day, @rodadas * 2.2, @dtInicio)
WHILE @dtInicio <= @dtFinal
BEGIN
	SET @timesRodada = 1
	WHILE @timesRodada <= @numTimes/4
	BEGIN
		IF (DATEPART( DW,@dtInicio )) = 1
		BEGIN
			EXEC(@sqlDomFaseB)
			IF @timesRodada = @numTimes/4
			BEGIN
				UPDATE Jogos SET Data = @dtInicio WHERE Data IS NULL;	
			END
		END
		ELSE
		BEGIN
			IF (DATEPART( DW,@dtInicio )) = 4
			BEGIN
				EXEC(@sqlQuaFaseB)
				IF @timesRodada = @numTimes/4
				BEGIN
					UPDATE Jogos SET Data = @dtInicio WHERE Data IS NULL;	
				END
			END
		END
		SET @timesRodada = @timesRodada + 1
	END
	SET @dtInicio = DATEADD(Day, 1, @dtInicio)
END

SET @dtFinal = DATEADD(Day, @rodadas * 2.2, @dtInicio)
WHILE @dtInicio <= @dtFinal
BEGIN
	SET @timesRodada = 1
	WHILE @timesRodada <= @numTimes/4
	BEGIN
		IF (DATEPART( DW,@dtInicio )) = 1
		BEGIN
			EXEC(@sqlDomFaseC)
			IF @timesRodada = @numTimes/4
			BEGIN
				UPDATE Jogos SET Data = @dtInicio WHERE Data IS NULL;	
			END
		END
		ELSE
		BEGIN
			IF (DATEPART( DW,@dtInicio )) = 4
			BEGIN
				EXEC(@sqlQuaFaseC)
				IF @timesRodada = @numTimes/4
				BEGIN
					UPDATE Jogos SET Data = @dtInicio WHERE Data IS NULL;	
				END
			END
		END
		SET @timesRodada = @timesRodada + 1
	END
	SET @dtInicio = DATEADD(Day, 1, @dtInicio)
END




/* TESTE


IF (OBJECT_ID( 'sp_jogos' ) IS NOT NULL)
	DECLARE @dtInicio SMALLDATETIME, @trigger INT
	SET @dtInicio = '2016-01-30'--CONVERT( SMALLDATETIME,'01/09/2016',103 )
	SET @trigger = 0
	EXEC sp_jogos @trigger, @dtInicio
	SELECT * FROM Jogos
GO
*/


/*--------------------------------------------------------------------------------------------
* TRIGGERS

*/


IF (OBJECT_ID('t_times') IS NOT NULL)
  DROP TRIGGER t_times
GO

CREATE TRIGGER t_times ON Times 
INSTEAD OF INSERT, DELETE, UPDATE 
AS 
BEGIN
	ROLLBACK TRANSACTION
	select 'Não é possível realizar essa operação na Tabela TIMES' 
END 
GO

IF (OBJECT_ID('t_grupos') IS NOT NULL)
  DROP TRIGGER t_grupos
GO

CREATE TRIGGER t_grupos ON Grupos 
INSTEAD OF INSERT, DELETE, UPDATE 
AS 
BEGIN
	ROLLBACK TRANSACTION
	select 'Não é possível realizar essa operação na Tabela GRUPOS!' 
END 
GO

IF (OBJECT_ID('t_jogos') IS NOT NULL)
  DROP TRIGGER t_jogos
GO

CREATE TRIGGER t_jogos ON Jogos 
INSTEAD OF INSERT, DELETE 
AS 
BEGIN
	ROLLBACK TRANSACTION
	select 'Não é possível realizar essa operação na Tabela JOGOS!' 
END 
GO

-- TESTE
/*
ALTER TABLE Times DISABLE TRIGGER ALL
ALTER TABLE Times ENABLE TRIGGER ALL
ALTER TABLE Grupos ENABLE TRIGGER ALL
ALTER TABLE Grupos DISABLE TRIGGER ALL
*/

/*--------------------------------------------------------------------------------------------
* UDF GRUPOS

*/


IF (OBJECT_ID('fn_grupos') IS NOT NULL)
  DROP TRIGGER fn_grupos
GO

CREATE FUNCTION fn_grupos(@nomeGrupo CHAR(1))
RETURNS @tabela table (
nome_time VARCHAR(150),
num_jogos_disputados INT,
vitorias INT,
empates INT, 
derrotas INT,
gols_marcados INT,
gols_sofridos INT,
saldo_gols INT,
pontos INT)
AS
BEGIN
	DECLARE @int INT,
	@nome_time VARCHAR(150),
	@jogos_disputados INT,
	@vitorias INT,
	@empates INT,
	@derrotas INT,
	@gols_marcados INT,
	@gols_sofridos INT,
	@saldo_gols INT,
	@pontos INT

	SET @int = 1
	WHILE(@int <= 20)
		BEGIN
			SET @nome_time = (SELECT nomeTime 
			FROM times ti INNER JOIN grupos gr 
			ON ti.codigoTime = gr.codigoTime 
			WHERE gr.grupo = @nomeGrupo AND ti.codigoTime = @int)
			
			SET @jogos_disputados = (SELECT COUNT(*) 
			FROM jogos 
			WHERE codigoTimeA = @int OR codigoTimeB = @int)
			
			SET @vitorias = (SELECT COUNT(*) 
			FROM jogos 
			WHERE (codigoTimeA = @int AND golsTimeA > golsTimeB) OR (codigoTimeB = @int AND golsTimeB > golsTimeA))
			
			SET @empates = (SELECT COUNT(*) 
			FROM jogos 
			WHERE (codigoTimeA = @int AND golsTimeA = golsTimeB) OR (codigoTimeB = @int AND golsTimeB = golsTimeA))
			
			SET @derrotas = (SELECT COUNT(*) 
			FROM jogos 
			WHERE (codigoTimeA = @int AND golsTimeA < golsTimeB) OR (codigoTimeB = @int AND golsTimeB < golsTimeA))
			
			SET @gols_marcados = (SELECT SUM(golsTimeA) 
			FROM jogos 
			WHERE codigoTimeA = @int ) + (SELECT SUM(golsTimeB) 
			FROM jogos 
			WHERE codigoTimeB = @int )
			
			SET @gols_sofridos = (SELECT SUM(golsTimeB) 
			FROM jogos 
			WHERE codigoTimeA = @int ) 
			+ (SELECT SUM(golsTimeA) 
			FROM jogos 
			WHERE codigoTimeB = @int )
			
			SET @saldo_gols = @gols_marcados - @gols_sofridos
			SET @pontos = (@vitorias * 3) + (@empates * 1 ) + (@derrotas * 0)	
		IF (@nome_time IS NOT NULL)
		BEGIN
			INSERT INTO @tabela VALUES
			(@nome_time, @jogos_disputados, @vitorias , @empates, @derrotas, @gols_marcados, @gols_sofridos, @saldo_gols, @pontos)
		END
	SET @int = @int + 1
	END
RETURN
END

/*--TESTE
SELECT * FROM fn_grupos('A')

*/

/*--------------------------------------------------------------------------------------------
* UDF CAMPEONATO

*/

IF (OBJECT_ID('fn_campeonato') IS NOT NULL)
  DROP TRIGGER fn_campeonato
GO


CREATE FUNCTION fn_campeonato()
RETURNS @tabela table (
nome_time VARCHAR(150),
num_jogos_disputados INT,
vitorias INT,
empates INT, 
derrotas INT,
gols_marcados INT,
gols_sofridos INT,
saldo_gols INT,
pontos INT)
AS
BEGIN
	DECLARE @int INT,
	@nome_time VARCHAR(150),
	@jogos_disputados INT,
	@vitorias INT,
	@empates INT,
	@derrotas INT,
	@gols_marcados INT,
	@gols_sofridos INT,
	@saldo_gols INT,
	@pontos INT

	SET @int = 1
	WHILE(@int <= 20)
	BEGIN
		SET @nome_time = (SELECT nomeTime 
		FROM times ti  
		WHERE ti.codigoTime = @int)
		
		SET @jogos_disputados = (SELECT COUNT(*) 
		FROM jogos 
		WHERE codigoTimeA = @int OR codigoTimeB = @int)
		
		SET @vitorias = (SELECT COUNT(*) 
		FROM jogos 
		WHERE (codigoTimeA = @int AND golsTimeA > golsTimeB) OR (codigoTimeB = @int AND golsTimeB > golsTimeA))
		
		SET @empates = (SELECT COUNT(*) 
		FROM jogos 
		WHERE (codigoTimeA = @int AND golsTimeA = golsTimeB) OR (codigoTimeB = @int AND golsTimeB = golsTimeA))
		
		SET @derrotas = (SELECT COUNT(*) 
		FROM jogos 
		WHERE (codigoTimeA = @int AND golsTimeA < golsTimeB) OR (codigoTimeB = @int AND golsTimeB < golsTimeA))
		
		SET @gols_marcados = (SELECT SUM(golsTimeA) 
		FROM jogos 
		WHERE codigoTimeA = @int ) 
		+ (SELECT SUM(golsTimeB) 
		FROM jogos 
		WHERE codigoTimeB = @int )
		
		SET @gols_sofridos = (SELECT SUM(golsTimeB) 
		FROM jogos 
		WHERE codigoTimeA = @int ) + (SELECT SUM(golsTimeA) 
		FROM jogos 
		WHERE codigoTimeB = @int )
		
		SET @saldo_gols = @gols_marcados - @gols_sofridos
		SET @pontos = (@vitorias * 3) + (@empates * 1 ) + (@derrotas * 0)
		
		IF (@nome_time IS NOT NULL)
		BEGIN
		INSERT INTO @tabela VALUES
		(@nome_time, @jogos_disputados, @vitorias , @empates, @derrotas, @gols_marcados, @gols_sofridos, @saldo_gols, @pontos)
		END
		SET @int = @int + 1
	END
	RETURN
END

/*--TESTE
SELECT * FROM fn_campeonato() ORDER BY  pontos DESC

*/

/*--------------------------------------------------------------------------------------------
* UDF QUARTA-FINAL

*/

CREATE FUNCTION fn_quartaFinal()
RETURNS @tabela table(
codigoTime1 INT)
AS
BEGIN
	INSERT INTO @tabela (codigoTime1) 
	SELECT TOP 2 ti.codigoTIme 
	FROM times ti INNER JOIN fn_grupos('A') fn 
	ON fn.nome_time = ti.nomeTime 
	ORDER BY fn.pontos DESC
			
	INSERT INTO @tabela (codigoTime1) 
	SELECT TOP 2 ti.codigoTIme 
	FROM times ti INNER JOIN fn_grupos('B') fn 
	ON fn.nome_time = ti.nomeTime 
	ORDER BY fn.pontos DESC		

	INSERT INTO @tabela (codigoTime1) 
	SELECT TOP 2 ti.codigoTIme 
	FROM times ti INNER JOIN fn_grupos('C') fn 
	ON fn.nome_time = ti.nomeTime 
	ORDER BY fn.pontos DESC
		
	INSERT INTO @tabela (codigoTime1) 
	SELECT TOP 2 ti.codigoTIme 
	FROM times ti INNER JOIN fn_grupos('D') fn 
	ON fn.nome_time = ti.nomeTime 
	ORDER BY fn.pontos DESC
	RETURN
END

--TESTE
SELECT * FROM fn_quartaFinal()


/*
* TESTES
*/

SELECT * FROM Grupos
SELECT * FROM Jogos
 


-- UPDATE GOLS

				DECLARE @dt SMALLDATETIME
				SET @dt = '2016-01-31'
				UPDATE Jogos SET GolsTimeA = 2, GolsTimeB = 3 
				WHERE CodigoTimeA = 7 AND CodigoTimeB = 8 AND Data = @dt

				SELECT * FROM Jogos
				
-- CONSULTA TIMES
				
				SELECT j.Data, j.CodigoTimeA as CodA, a.NomeTime  as 'Time A', j.CodigoTimeB  as CodB, b.NomeTime as 'Time B' 
				FROM Jogos j 
				INNER JOIN Times a 
				ON a.CodigoTime = j.CodigoTimeA 
				INNER JOIN Times b 
				ON b.CodigoTime = j.CodigoTimeB 

-- CONSULTA TIMES POR CLUBE
				 
				SELECT j.Data, j.CodigoTimeA as CodA, a.NomeTime  as 'Time A', j.CodigoTimeB  as CodB, b.NomeTime as 'Time B' 
				FROM Jogos j 
				INNER JOIN Times a 
				ON a.CodigoTime = j.CodigoTimeA 
				INNER JOIN Times b 
				ON b.CodigoTime = j.CodigoTimeB
				WHERE CodigoTimeA LIKE 2
				 
				 SELECT * FROM Times
				 WHERE nomeTime LIKE '%Audax%'
				 
-- CONSULTA TIMES POR DATA
				
				DECLARE @dt SMALLDATETIME SET @dt = '2016-01-31'
				
				SELECT j.Data, j.CodigoTimeA as CodA, a.NomeTime  as 'Time A', j.CodigoTimeB  as CodB, b.NomeTime as 'Time B' 
				FROM Jogos j 
				INNER JOIN Times a 
				ON a.CodigoTime = j.CodigoTimeA 
				INNER JOIN Times b 
				ON b.CodigoTime = j.CodigoTimeB
				WHERE j.Data = @dt
