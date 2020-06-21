[[Home](./home.md)]

## Cursores

#### SINTAXE:

```sql

/*
    DECLARE nome_cursor CURSOR FOR
-- consulta que retorna registros que serão lidos pelo cursor

    OPEN nome_cursor --Abre o cursor
    FETCH NEXT FROM nome_cursor --Buscar próximo registro
    INTO -- variáveis

    WHILE @@FETCH_STATUS = 0 --Enquanto não há erro de busca (ou seja, enquanto há próximo registro)
    BEGIN

        Linha de Programação

        FETCH NEXT FROM nome_cursor
        INTO variáveis

    END
CLOSE nome_cursor --Encerra Cursor
DEALLOCATE nome_cursor --Tira o cursor da memória

*/

--Exemplo

use testejoin

select * from pilotos
select * from motor
select * from equipe
select * from carroequipepiloto

CREATE FUNCTION fn_relatorio()
RETURNS @tabela TABLE(
num_carro int,
piloto varchar(100),
equipe varchar(100),
motor varchar(100))
AS
BEGIN

DECLARE @id_piloto INT
DECLARE @num_carro INT
DECLARE @piloto VARCHAR(100)
DECLARE @equipe VARCHAR(100)
DECLARE @motor VARCHAR(100)

    DECLARE buscapiloto_cursor CURSOR FOR
    SELECT DISTINCT id FROM pilotos

    OPEN buscapiloto_cursor
    FETCH NEXT FROM buscapiloto_cursor
    INTO @id_piloto

    WHILE @@FETCH_STATUS = 0
    BEGIN

        IF (@id_piloto NOT IN (SELECT id FROM pilotos
        LEFT OUTER JOIN carroequipepiloto
        ON pilotos.id = carroequipepiloto.id_piloto
        WHERE carroequipepiloto.id_piloto is null))
        BEGIN
        SELECT @num_carro = carroequipepiloto.num_carro, @piloto = pilotos.nome, @equipe = equipe.nome,
            @motor = motor.nome
        FROM pilotos INNER JOIN carroequipepiloto
        ON pilotos.id = carroequipepiloto.id_piloto
        INNER JOIN equipe
        ON equipe.id =  carroequipepiloto.id_equipe
        INNER JOIN motor
        ON motor.id =  carroequipepiloto.id_motor
        WHERE pilotos.id = @id_piloto

        INSERT INTO @tabela VALUES
        (@num_carro, @piloto, @equipe, @motor)

        END

        FETCH NEXT FROM buscapiloto_cursor
        INTO @id_piloto
    END
CLOSE buscapiloto_cursor
DEALLOCATE buscapiloto_cursor

RETURN

END

SELECT * FROM fn_relatorio()


```
