[Home](./home.md) | [Apresentação](./fase-02-intro.md) | Banco de Dados | [ Telas ](./fase-02-telas.md)

```sql

/*
* Laboratório de Banco de Dados
* Prof. M.Sc. Leandro Colevati dos Santos
* AVALIAÇÃO 2: "Campeonato Paulista 2016"
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
    select 'N?o ? poss?vel realizar essa opera??o na Tabela TIMES'

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
    select 'N?o ? poss?vel realizar essa opera??o na Tabela GRUPOS!'
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
    select 'N?o ? poss?vel realizar essa opera??o na Tabela JOGOS!'
END
GO


-- TESTE
ALTER TABLE Times DISABLE TRIGGER ALL
ALTER TABLE Times ENABLE TRIGGER ALL
ALTER TABLE Grupos ENABLE TRIGGER ALL
ALTER TABLE Grupos DISABLE TRIGGER ALL



/*
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
            WHERE (codigoTimeA = @int AND golsTimeA > golsTimeB)
                        OR (codigoTimeB = @int AND golsTimeB > golsTimeA))

            SET @empates = (SELECT COUNT(*)
            FROM jogos
            WHERE (codigoTimeA = @int AND golsTimeA = golsTimeB)
                        OR (codigoTimeB = @int AND golsTimeB = golsTimeA))

            SET @derrotas = (SELECT COUNT(*)
            FROM jogos
            WHERE (codigoTimeA = @int AND golsTimeA < golsTimeB)
                        OR (codigoTimeB = @int AND golsTimeB < golsTimeA))

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


--TESTE
SELECT * FROM fn_grupos('A')



/*
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
        WHERE (codigoTimeA = @int AND golsTimeA > golsTimeB)
                OR (codigoTimeB = @int AND golsTimeB > golsTimeA))

        SET @empates = (SELECT COUNT(*)
        FROM jogos
        WHERE (codigoTimeA = @int AND golsTimeA = golsTimeB)
                OR (codigoTimeB = @int AND golsTimeB = golsTimeA))

        SET @derrotas = (SELECT COUNT(*)
        FROM jogos
        WHERE (codigoTimeA = @int AND golsTimeA < golsTimeB)
                OR (codigoTimeB = @int AND golsTimeB < golsTimeA))

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
        (@nome_time, @jogos_disputados, @vitorias , @empates, @derrotas,
                 @gols_marcados, @gols_sofridos, @saldo_gols, @pontos)

        END
        SET @int = @int + 1
    END
    RETURN
END


--TESTE
SELECT * FROM fn_campeonato() ORDER BY pontos DESC


/*
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

-- TESTE
SELECT * FROM fn_quartaFinal()


```
