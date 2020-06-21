[[Home](home.md)]

```sql

CREATE FUNCTION fn_reprovados()
RETURNS @tabela TABLE(
id INT,
nome VARCHAR(MAX),
reprovados INT)
AS
BEGIN
    DECLARE @id_materia INT,
            @reprovados INT,
            @nome_materia VARCHAR(MAX)
    DECLARE c_busca CURSOR FOR
    SELECT DISTINCT id, nome FROM materias

    OPEN c_busca
    FETCH NEXT FROM c_busca
    INTO @id_materia, @nome_materia

    WHILE @@FETCH_STATUS = 0
    BEGIN

        SELECT @reprovados = COUNT(*)
        FROM alunos al
        INNER JOIN alunomateria am
        ON al.ra = am.ra_aluno
        INNER JOIN materias mat
        ON mat.id = am.id_materia
        WHERE mat.id = @id_materia
            AND al.ra NOT IN
        (
        SELECT al.ra
        FROM alunos al
        INNER JOIN notas nt
        ON al.ra = nt.ra_aluno
        INNER JOIN avaliacoes av
        ON av.id = nt.id_avaliacao
        INNER JOIN materias mat
        ON mat.id = nt.id_materia
        WHERE mat.id = @id_materia
        GROUP BY al.ra
        HAVING CAST(SUM(av.peso * nt.nota) AS DECIMAL(7,1)) >= 6.0
        )
/*
        PRINT @nome_materia+' - '+
            CAST(@reprovados AS VARCHAR(3))+
            ' reprovados'
*/

        INSERT INTO @tabela VALUES
        (@id_materia, @nome_materia, @reprovados)

        FETCH NEXT FROM c_busca
        INTO @id_materia, @nome_materia
    END
    CLOSE c_busca
    DEALLOCATE c_busca

    RETURN
END

SELECT * FROM fn_reprovados()


```
