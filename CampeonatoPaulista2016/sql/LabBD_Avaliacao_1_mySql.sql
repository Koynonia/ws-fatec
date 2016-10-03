/*
* Laboratório de Banco de Dados
* Prof. M.Sc. Leandro Colevati dos Santos
* 
* AVALIAÇÃO 1: "Campeonato Paulista 2016"
*/

/*
* Criação da Base de Dados "Campeonato"
*/

DROP DATABASE IF EXISTS campeonato;

CREATE DATABASE campeonato;

USE campeonato;

/*
* Criação da Tabela "Times" e preenchimento com os registros de 20 times 
*/

DROP TABLE IF EXISTS times;

CREATE TABLE times(
codigoTime INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
nomeTime VARCHAR(100) NOT NULL,
cidade VARCHAR(50) NOT NULL,
estadio VARCHAR(30) NOT NULL
);

INSERT INTO times VALUES 
(DEFAULT,'Esporte Clube Água Santa', 'Diadema', 'Distrital do Inamar'), 
(DEFAULT,'Grêmio Osasco Audax', 'Osasco', 'José Liberatti'), 
(DEFAULT,'Botafogo Futebol Clube', 'Ribeirão Preto', 'Santa Cruz'), 
(DEFAULT,'Capivariano Futebol Clube', 'Capivari', 'Arena Capivari'), 
(DEFAULT,'Sport Club Corinthians Paulista', 'São Paulo', 'Arena Corinthians'), 
(DEFAULT,'Associação Ferroviária de Esportes', 'Araraquara', 'Fonte Luminosa'), 
(DEFAULT,'Ituano Futebol Clube', 'Itu', 'Novelli Júnior'), 
(DEFAULT,'Clube Atlético Linense', 'Lins', 'Gilberto Siqueira Lopes'), 
(DEFAULT,'Mogi Mirim Esporte Clube', 'Mogi Mirim', 'Vail Chaves'), 
(DEFAULT,'Grêmio Novorizontino', 'Novo Horizonte', 'Jorge Ismael de Biasi'), 
(DEFAULT,'Oeste Futebol Clube', 'Itápolis', 'Amaros'), 
(DEFAULT,'Sociedade Esportiva Palmeiras', 'São Paulo', 'Allianz Parque'), 
(DEFAULT,'Associação Atlética Ponte Preta', 'Campinas', 'Moisés Lucarelli'), 
(DEFAULT,'Red Bull Brasil', 'Campinas', 'Moisés Lucarelli'), 
(DEFAULT,'Rio Claro Futebol Clube', 'Rio Claro', 'Augusto Schmidt Filho'), 
(DEFAULT,'Santos Futebol Clube', 'Santos', 'Vila Belmiro'), 
(DEFAULT,'Esporte Clube São Bento', 'Sorocaba', 'Walter Ribeiro'), 
(DEFAULT,'São Bernardo Futebol Clube', 'São Bernardo do Campo', 'Primeiro de Maio'), 
(DEFAULT,'São Paulo Futebol Clube', 'São Paulo', 'Morumbi'), 
(DEFAULT,'Esporte Clube XV de Novembro', 'Piracicaba', 'Barão de Serra Negra');

/*
* Criação da Tabela "Grupos" 
*/

DROP TABLE IF EXISTS grupos;

CREATE TABLE grupos(
grupo CHAR(1), 
codigoTime INT NOT NULL, 
FOREIGN KEY (codigoTime) 
REFERENCES times(codigoTime)
);

/*
* Criação da Tabela "Jogos" 
*/

DROP TABLE IF EXISTS jogos;

CREATE TABLE jogos(
codigoTimeA INT, 
codigoTimeB INT, 
golsTimeA INT, 
golsTimeB INT,
datas DATE,
FOREIGN KEY (codigoTimeA) 
REFERENCES Times(CodigoTime), 
FOREIGN KEY (CodigoTimeB) 
REFERENCES Times(CodigoTime)
)



/*
* 1) Procedure que divide os times nos quatro grupos, preenchendo, aleatoriamente 
* (exceção da regra: Coritnthians, Palmeiras, Santos e São Paulo NÃO PODEM estar no mesmo grupo).
*/

IF (OBJECT_ID('sp_grupos') IS NOT NULL)
  DROP PROCEDURE sp_grupos
GO

CREATE PROCEDURE sp_grupos 
AS DECLARE 	@numTimes INT, 
			@numGrupos INT, 
			@numTimesPorGrupo INT, 
			@contador INT,
			@grupo CHAR,
			@sqlTimes VARCHAR(MAX),
			@sqlGrupo VARCHAR(MAX)

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
*/

IF (OBJECT_ID('sp_grupos') IS NOT NULL)
	EXEC sp_grupos
	SELECT * FROM Grupos
GO

/*
* 2) Procedure que gera as rodadas dos jogos: 
* A primeira fase ocorrerá em 15 datas seguidas, 
* sempre rodada cheia (os 10 jogos com todos os 20 times), 
* aos domingos e quartas
*/

IF (OBJECT_ID('sp_jogos') IS NOT NULL)
  DROP PROCEDURE sp_jogos
GO

CREATE PROCEDURE sp_jogos (@dtInicio SMALLDATETIME)
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

DELETE FROM Jogos			
SET @rodadas = 15
SELECT @numTimes = COUNT(CodigoTime) FROM Times

SET @sqlDomFaseA = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''A'') a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''B''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'


SET @sqlQuaFaseA = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''C''
								ORDER BY NEWID()) a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''D''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'

SET @sqlDomFaseB = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''A'') a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''D''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'

SET @sqlQuaFaseB = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''B''
								ORDER BY NEWID()) a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''C''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'

SET @sqlDomFaseC = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''A'') a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeB AND j.Data IS NULL
								WHERE j.CodigoTimeB IS NULL AND g.Grupo = ''C''
								ORDER BY NEWID()) b
							ON a.CodigoTime IS NOT NULL AND b.CodigoTime IS NOT NULL'

SET @sqlQuaFaseC = 'INSERT Jogos(CodigoTimeA, CodigoTimeB)
							SELECT a.CodigoTime, b.CodigoTime 
							FROM (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
								ON g.CodigoTime = j.CodigoTimeA AND j.Data IS NULL
								WHERE j.CodigoTimeA IS NULL AND g.Grupo = ''B''
								ORDER BY NEWID()) a
							JOIN (
								SELECT TOP 1 g.CodigoTime 
								FROM Grupos AS g 
								LEFT JOIN Jogos AS j 
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

/*
* TESTE
*/

IF (OBJECT_ID( 'sp_jogos' ) IS NOT NULL)
	DECLARE @dtInicio SMALLDATETIME
	SET @dtInicio = '2016-01-30'--CONVERT( SMALLDATETIME,'01/09/2016',103 )
	EXEC sp_jogos @dtInicio
	SELECT * FROM Jogos
GO

/*
* TESTES
*/

SELECT * FROM Grupos
 
SELECT j.CodigoTimeA,j.CodigoTimeB, j.Data 
FROM Jogos AS j 
WHERE j.CodigoTimeA = 9

SELECT  g.Grupo, t.NomeTime 
FROM Times AS t
INNER JOIN Grupos AS g
ON g.CodigoTime = t.CodigoTime 
--WHERE g.Grupo LIKE 'A'
GROUP BY g.Grupo, t.NomeTime
ORDER BY g.Grupo ASC, t.NomeTime DESC

SELECT a.Data, a.NomeTime AS 'Time A', b.NomeTime AS 'Time B' 
FROM(
SELECT j.Data, t.NomeTime
FROM Jogos AS j
INNER JOIN Times AS t 
ON j.CodigoTimeA = t.CodigoTime) a 
INNER JOIN(
SELECT j.Data, t.NomeTime
FROM Jogos AS j
INNER JOIN Times AS t 
ON j.CodigoTimeB = t.CodigoTime) b
ON a.Data = b.Data

SELECT CodigoTimeB, Count(*)AS 'Total' FROM Jogos AS j
WHERE j.CodigoTimeA = 5
GROUP BY CodigoTimeB
HAVING Count(*) > 1

SELECT Data, Count(*) AS 'Total' FROM Jogos AS j
GROUP BY Data