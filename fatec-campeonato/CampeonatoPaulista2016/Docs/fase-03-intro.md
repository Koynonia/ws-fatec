[Home](./home.md) | Apresentação | [Banco de Dados](./fase-03-bd.md) | [ Telas ](./fase-03-telas.md)

# Bem-vindo à Fase 03 (Final)!

Aqui descrevemos os detalhes do projeto para a avaliação 3 da matéria de Laboratório de Banco de Dados do Curso de Análise e Desenvolvimento de Software ministrada pelo Prof. M. Sc. Leandro Colevati dos Santos na FATEC Zona Leste no segundo semestre de 2016.

## Apresentação do Projeto

Considere que, no SGBD SQL SERVER, a partir da versão 2005, a query para se verificar quais DATABASES existem no sistema e sua data e hora de criação é:

```sql
SELECT name, crdate FROM sys.sysdatabases
```

Essencialmente, em cada servidor de banco de dados, pode haver um número diferente de DATABASES e, estas, podem ter nomes diferentes.

Considere que é inseguro manter, exclusivamente, a versão que está em uso, ou seja, sem um Backup de Segurança.

Você deve fazer uma aplicação Java em que:

* permita, ao usuário, verificar, em uma página, o nome, o dia de criação (formatado dd/MM/yyyy) e a hora de criação (formatado HH:mm), das databases contidas no SGBD.

* permita ao usuário, a partir de uma pasta do em uma pasta do projeto, rodar uma procedure que tenha, internamente a ela, um cursor que faça FULL Backup de todas as DATABASES do SGBD, atentando que a DATABASE tempdb deve ser DESPREZADA. Todos os arquivos .bak devem ter o nome da DATABASE.

* permita ao usuário, a partir de uma pasta do em uma pasta do projeto, rodar uma procedure que faça FULL Backup de uma DATABASE do SGBD, selecionada pelo usuário, atentando que a DATABASE tempdb deve ser DESPREZADA. O arquivos .bak devem ter o nome da DATABASE.

* permita ao usuário escolher um arquivo .bak, contida em uma pasta do projeto, verificar se aquela DATABASE já existe, avisar ao usuário que, se ele optar por continuar, a DATABASE existente será sobrescrita. Caso ele opte pela opção positiva, chamar uma procedure que faz a Restauração da DATABASE específica.

* permita ao usuário pegar a lista de diretórios .bak daquele diretório e, enviar para uma procedure que faça a restauração forçada de todas as DATABASES.

Data de Entrega: 28 / 11 / 2016

Have fun!
