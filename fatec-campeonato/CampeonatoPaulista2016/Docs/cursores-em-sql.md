[[ Home ](home.md)]

# Cursores em SQL

## O que são e como se constroem os cursores em SQL.

Por [Claudio](http://www.criarweb.com/contato/contacta.php?destinatario=100) (Publicado em: 03/1/08)
Fonte: [criarweb.com](http://www.criarweb.com/artigos/cursores-sql.html)

Em alguns SGDB é possível a abertura de **cursores** de dados desde o próprio ambiente de trabalho, para isso se utilizam, normalmente procedimentos armazenados. A sintaxe para definir um cursor é a seguinte:

```sql

DECLARE
nome-cursor
FOR
especificacao-consulta
[ORDER BY]

```

Por exemplo:

```sql

DECLARE
    Meu_Cursor
FOR
   SELECT num_emp, nome, posto, salario
   FROM empregados
   WHERE num_dept = 'informatica'

```

Este comando é meramente declarativo, simplesmente especifica as filas e colunas que iremos recuperar. A consulta se executa quando se abre ou se ativa o cursor. A cláusula [ORDER BY] é opcional e especifica uma ordenação para as filas do cursor; se não se especifica, a ordenação das filas é definida pelo gerenciador de SGBD.

Para abrir ou ativar um cursor se utiliza o comando **OPEN** do SQL, a sintaxe é a seguinte:

```sql

OPEN
nome-cursor
[USING lista-variaveis]

```

Ao abrir o cursor, avalia-se a consulta que aparece em sua definição, utilizando os valores atuais de qualquer parâmetro referenciado na consulta, para produzir uma coleção de filas. O ponteiro se posiciona diante da primeira fila de dados (registro atual), esta sentença não recupera nenhuma fila.

Uma vez aberto o cursor, utiliza-se a cláusula **FETCH** para recuperar as filas do cursor, a sintaxe é a seguinte:

```sql

FETCH
nome-cursor
INTO
lista-variaveis

```

**Lista-variaveis** são as variáveis que vão conter os dados recuperados da fila do cursor, na definição devem ir separadas por vírgulas. Na lista de variáveis, deve-se definir tantas variáveis como colunas quantas tiver a fila a recuperar.

Para fechar um cursor, utiliza-se o comando **CLOSE**, este comando faz desaparecer o ponteiro sobre o registro atual. A sintaxe é:

```sql

CLOSE
nome-cursor

```

Por último, e para eliminar o cursor, utiliza-se o comando **DROP CURSOR**. Sua sintaxe é a seguinte:

```sql

DROP CURSOR
nome-cursor

```

## Exemplo (sobre SQL-SERVER):

### Abrir um cursor e percorrê-lo

```sql

DECLARE Employee_Cursor CURSOR FOR
SELECT LastName, FirstName
FROM Northwind.dbo.Employees
WHERE LastName like 'B%'
OPEN Employee_Cursor

FETCH NEXT FROM Employee_Cursor

WHILE @@FETCH_STATUS = 0
BEGIN

FETCH NEXT FROM Employee_Cursor

END
CLOSE Employee_Cursor
DEALLOCATE Employee_Cursor

```

### Abrir um cursor e imprimir seu conteúdo

```sql

SET NOCOUNT ON
DECLARE
@au_id varchar(11),
@au_fname varchar(20),
@au_lname varchar(40),
@message varchar(80),
@title varchar(80)

PRINT "-------- Utah Authors report --------"

DECLARE authors_cursor CURSOR FOR
SELECT au_id, au_fname, au_lname
FROM autho
WHERE state = "UT"
ORDER BY au_id

OPEN authors_cursor
FETCH NEXT FROM authors_cursor
INTO @au_id, @au_fname, @au_lname

WHILE @@FETCH_STATUS = 0
BEGIN

PRINT " "

SELECT

@message = "----- Books by Author: " +

@au_fname + " " + @au_lname

PRINT @message

DECLARE titles_cursor CURSOR FOR

SELECT t.title

FROM titleauthor ta, titles t

WHERE ta.title_id = t.title_id AND ta.au_id = au_id

OPEN titles_cursor

FETCH NEXT FROM titles_cursor INTO @title

IF @@FETCH_STATUS <> 0

PRINT " <<No Books>>"

WHILE @@FETCH_STATUS = 0

BEGIN

SELECT @message = " " + @title

PRINT @message

FETCH NEXT FROM titles_cursor INTO @title

END

CLOSE titles_cursor

DEALLOCATE titles_cursor

FETCH NEXT FROM authors_cursor

INTO @au_id, @au_fname, @au_lname
END 
CLOSE authors_cursor
DEALLOCATE authors_cursor
GO

```

### Percorrer um cursor

```sql

USE pubs
GO
DECLARE authors_cursor CURSOR FOR
SELECT au_lname
FROM authors
WHERE au_lname LIKE "B%"
ORDER BY au_lname

OPEN authors_cursor
FETCH NEXT FROM authors_cursor
WHILE @@FETCH_STATUS = 0
BEGIN

FETCH NEXT FROM authors_cursor

END
CLOSE authors_cursor
DEALLOCATE authors_cursor

```

### Percorrer um cursor salvando os valores em variáveis

```sql

USE pubs
GO
DECLARE @au_lname varchar(40)
DECLARE @au_fname varchar(20)

DECLARE authors_cursor CURSOR FOR
SELECTau_lname, au_fname
FROM authors
WHERE au_lname LIKE "B%"
ORDER BY au_lname, au_fname

OPEN authors_cursor
FETCH NEXT FROM authors_cursor INTO @au_lname, @au_fname
WHILE @@FETCH_STATUS = 0
BEGIN

PRINT "Author: " + @au_fname + " " + @au_lname

FETCH NEXT FROM authors_cursor

INTO @au_lname, @au_fname

END
CLOSE authors_cursor
DEALLOCATE authors_cursor

```
