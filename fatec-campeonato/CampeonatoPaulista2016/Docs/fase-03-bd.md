[Home](home.md) | [Apresentação](fase-03-intro.md) | Banco de Dados | [ Telas ](fase-03-telas.md)

```sql

/*
* Laboratório de Banco de Dados
* Prof. M.Sc. Leandro Colevati dos Santos
* AVALIAÇÃO 3: "Campeonato Paulista 2016"
*
* FUNCAO QUE DEVOLVE UMA TABELA COM AS BASES DE DADOS DO SGDB 
* SEU ID E AS DATAS E HORAS DE SUA CRIAÇÃO:
*
* 1) permita, ao usuário, verificar, em uma página, o nome, 
* o dia de criação (formatado dd/MM/yyyy) e a hora de criação (formatado HH:mm), 
* das databases contidas no SGBD.
*/

IF (OBJECT_ID('fn_databases') IS NOT NULL)
  DROP FUNCTION fn_databases
GO

CREATE FUNCTION fn_databases()
RETURNS @tabela table(id INT, nome VARCHAR(100), dtCriacao SMALLDATETIME)
AS
BEGIN

DECLARE @id INT
DECLARE @nome VARCHAR(150)
DECLARE @dtCriacao SMALLDATETIME

    -- Cria um cursor para selecionar todas as databases,  
    --  excluindo tempdb
    DECLARE buscadb_cursor CURSOR FOR
    SELECT DISTINCT dbid, name, crdate 
    FROM sys.sysdatabases 
    WHERE name NOT LIKE 'tempdb'
    ORDER BY dbid

    -- Abre o cursor e faz a primeira leitura
    OPEN buscadb_cursor
        FETCH NEXT FROM buscadb_cursor
        INTO @id, @nome, @dtCriacao
    -- Loop de leitura das databases selecionadas
        WHILE @@FETCH_STATUS = 0
        BEGIN
            -- Insere o registro da database na tabela
            INSERT INTO @tabela VALUES (@id, @nome, @dtCriacao)
            FETCH NEXT FROM buscadb_cursor
            INTO @id, @nome, @dtCriacao
        END
    -- Libera recursos alocados pelo cursor
    CLOSE buscadb_cursor
    DEALLOCATE buscadb_cursor
    RETURN
END

--TESTE
SELECT * FROM fn_databases()



/*
* PROCEDURE COM CURSOR PARA BACKUP DAS BASES DE DADOS DO SGDB:
*
* 2) permita ao usuário, a partir de uma pasta do em uma pasta do projeto, 
* rodar uma procedure que tenha, internamente a ela, um cursor que faça 
* FULL Backup de todas as DATABASES do SGBD, atentando que a DATABASE 
* tempdb deve ser DESPREZADA. Todos os arquivos .bak devem ter o nome da DATABASE.
*/

IF (OBJECT_ID('sp_backupbases') IS NOT NULL)
  DROP PROCEDURE sp_backupbases
GO

CREATE PROCEDURE sp_backupbases(@dir VARCHAR(256))
AS DECLARE  @caminho VARCHAR(256),
            @nome VARCHAR(150), 
            @arquivo VARCHAR(256)
            --@data VARCHAR(20) -- por data

-- Define caminho de destino do backup
SET @caminho = @dir

-- Define a data do backup
-- SELECT @data = CONVERT(VARCHAR(20),GETDATE(),112)

-- Cria um cursor para selecionar todas as databases,  
--  excluindo tempdb
DECLARE db_cursor CURSOR FOR
        SELECT name
        FROM sys.sysdatabases
        WHERE name NOT IN ('tempdb') 

-- Abre o cursor e faz a primeira leitura
OPEN db_cursor
FETCH NEXT FROM db_cursor INTO @nome

-- Loop de leitura das databases selecionadas
WHILE @@FETCH_STATUS = 0
BEGIN
   SET @arquivo = @caminho + @nome + '.bak' --'_' + @data + '.bak'

   -- Executa o backup para a database
   BACKUP DATABASE @nome TO DISK = @arquivo WITH FORMAT;

   FETCH NEXT FROM db_cursor INTO @nome
END

-- Libera recursos alocados pelo cursor
CLOSE db_cursor
DEALLOCATE db_cursor

-- TESTE
IF (OBJECT_ID('sp_backupbases') IS NOT NULL)
    EXEC sp_backupbases 'C:\Backup\'
GO


/*
* PROCEDURE PARA BACKUP DE UMA BASE DE DADOS DO SGDB:
*
* 3) permita ao usuário, a partir de uma pasta do em uma pasta do projeto,
* rodar uma procedure que faça FULL Backup de uma DATABASE do SGBD, selecionada
* pelo usuário, atentando que a DATABASE tempdb deve ser DESPREZADA.
* O arquivos .bak devem ter o nome da DATABASE.
*/

IF (OBJECT_ID('sp_backupbase') IS NOT NULL)
  DROP PROCEDURE sp_backupbase
GO

CREATE PROCEDURE sp_backupbase(@caminho VARCHAR(256), @nome VARCHAR(150))
AS DECLARE @arquivo VARCHAR(256)

-- Define caminho de destino do backup
SET @arquivo = @caminho + @nome + '.bak'

-- Executa o backup para a database
BACKUP DATABASE @nome TO DISK = @arquivo WITH FORMAT;

-- TESTE
IF (OBJECT_ID('sp_backupbase') IS NOT NULL)
    EXEC sp_backupbase 'C:\Backup\', 'Campeonato'
GO

/*
* PROCEDURE PARA RESTAURAR UMA BASE DE DADOS NO SGDB:
*
* 4) permita ao usuário escolher um arquivo .bak, contida em
* uma pasta do projeto, verificar se aquela DATABASE já existe,
* avisar ao usuário que, se ele optar por continuar, a DATABASE
* existente será sobrescrita. Caso ele opte pela opção positiva,
* chamar uma procedure que faz a Restauração da DATABASE específica.
*
* 5) permita ao usuário pegar a lista de diretórios .bak daquele diretório e,
* enviar para uma procedure que faça a restauração forçada de todas as DATABASES.
*/

IF (OBJECT_ID('sp_restaurabase') IS NOT NULL)
  DROP PROCEDURE sp_restaurabase
GO

CREATE PROCEDURE sp_restaurabase(@caminho VARCHAR(256), @nome VARCHAR(150))
AS DECLARE @arquivo VARCHAR(256)

-- Define caminho de destino do backup
SET @arquivo = @caminho + @nome + '.bak'

-- Executa o backup para a database
RESTORE DATABASE @nome FROM DISK = @arquivo WITH RECOVERY, REPLACE
RAISERROR(@nome,16,1 )

-- TESTE
IF (OBJECT_ID('sp_restaurabase') IS NOT NULL)
    DROP DATABASE s2b
    EXEC sp_restaurabase 'C:\Backup\', 's2b'
GO

```
