[[Home](home.md)]

```sql

--FULL BACKUP da Base de Dados

/*
BACKUP DATABASE nome_da_database
TO DISK = 'endereco_backup\nome_do_arquivo.bak'
*/

--Exemplo
BACKUP DATABASE procedimento
TO DISK = 'C:\Users\PC\Desktop\VM\procedimento.bak'


--RESTORE Completo da Base de Dados -- 1∫ Caso (Direto)
/*
RESTORE DATABASE nome_da_database
FROM DISK = 'caminho_do_arquivo_de_backup'
WITH RECOVERY
*/

--Exemplo
RESTORE DATABASE procedimento
FROM DISK = 'C:\Users\PC\Desktop\VM\procedimento.bak'
WITH RECOVERY

--RESTORE Completo da Base de Dados -- 2∫ Caso (RestriÁıes de SO)
--RESTORE WITH REPLACE sÛ deve ser utilizado quando se tem certeza da database que ser· substituÌda
--	OperaÁ„o n„o permite BEGIN TRANSACTION
/*
CREATE DATABASE nome_database

RESTORE DATABASE nome_da_database
FROM DISK = 'caminho_do_arquivo_de_backup'
WITH RECOVERY,
REPLACE
*/

--Exemplo
RESTORE DATABASE procedimento
FROM DISK = 'c:\procedimento.bak'
WITH RECOVERY,
REPLACE

```
