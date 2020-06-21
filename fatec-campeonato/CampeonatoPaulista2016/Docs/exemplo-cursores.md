[[Home](home.md)]

```sql


exec sp_buscapedidos
@cod_cli ='38987290710'


CREATE PROCEDURE sp_buscapedidos
@cod_cli char(11)

AS

DECLARE @data as date

DECLARE buscadata_cursor CURSOR FOR
SELECT DISTINCT data FROM pedido

OPEN buscadata_cursor
FETCH NEXT FROM buscadata_cursor
INTO @data

WHILE @@FETCH_STATUS = 0
BEGIN

    SELECT DISTINCT cliente.nome, produto.nome AS Produto, pedido.qtd,produto.valor as Valor_Un,
    CONVERT (CHAR(10),pedido.data,103) AS Data_Pedido
    FROM pedido inner join cliente
    ON cliente.cod = pedido.cod_cli
    inner join produto
    ON produto.cod = pedido.cod_prod
    WHERE pedido.data = @data and pedido.cod_cli = @cod_cli

    SELECT SUM(valor_total) as Total_Compra
    FROM pedido
    WHERE pedido.data = @data and pedido.cod_cli = @cod_cli

    FETCH NEXT FROM buscadata_cursor
    INTO @data

END
CLOSE buscadata_cursor
DEALLOCATE buscadata_cursor

```
