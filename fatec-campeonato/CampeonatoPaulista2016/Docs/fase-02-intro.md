[Home](./home.md) | Apresentação | [Banco de Dados](./fase-02-bd.md) | [ Telas ](./fase-02-telas.md)

# Bem-vindo à Fase 2!

Aqui descrevemos os detalhes do projeto para a avaliação 2 da matéria de Laboratório de Banco de Dados do Curso de Análise e Desenvolvimento de Software ministrada pelo Prof. M. Sc. Leandro Colevati dos Santos na FATEC Zona Leste no segundo semestre de 2016.

**Avaliação 2 – Entrega (31/10) Laboratório de Banco de Dados**

- Prof. M. Sc. Leandro Colevati dos Santos

Partindo do domínio da Avaliação 1, criar uma Trigger que não permita INSERT, UPDATE ou DELETE nas tabelas TIMES e GRUPOS e uma Trigger semelhante, mas apenas para INSERT e DELETE na tabela jogos.

Uma vez determinados os grupos e os jogos, as partidas serão disputadas e terão resultados em número de gols (Ex.: TimaA 3 X 0 TimeB). A cada rodada, os 10 jogos terão resultados.

Fazer uma tela que, pelas datas dos jogos, seja possível inserir os resultados dos jogos, que fará um UPDATE na tabela jogos, que já terá os times e data, com os gols marcados por cada time.

Fazer uma tela de consulta com os 4 grupos e 4 JTables, que mostre a saída (para cada JTable) de uma UDF (User Defined FUNCTION), que receba o nome do grupo, valide-o e dê a seguinte saída:

```sql

GRUPO (
nome_time,
num_jogos_disputados [1],
vitorias, empates,
derrotas,
gols_marcados,
gols_sofridos,
saldo_gols [2],
pontos [3]
)
```

O campeão de cada grupo se dará por aquele que tiver maior número de pontos. Em caso de empate, a ordem de desempate é por número de vitórias, depois por gols marcados e por fim, por saldo de gols.

O critério de rebaixamento também é pouco convencional.

Para definir os 4 rebaixados, se considera os times que tem menor pontuação dentre os 20 times, independente de qual grupo que pertença.

Na tela com as 4 JTables, deve-se mudar a cor de fundo da linha dos times que estiverem em condição de rebaixamento.

Deve-se fazer, para melhor visualização dos resultados, uma tela com a classificação geral, numa UDF (User Defined FUNCTION), que receba o nome do grupo, valide-o e dê a seguinte saída, para os 20 times do campeonato:

```sql

CAMPEONATO (
nome_time,
num_jogos_disputados [1],
vitorias,
empates,
derrotas,
gols_marcados,
gols_sofridos,
saldo_gols [2],
pontos [3]
)

```

A ordenação da saída se dá pelo mesmo critério anterior.

Por fim, uma tela deverá ser criada para ver a projeção das quartas de final. As quartas de final serão disputadas entre o 1º e o 2º de cada grupo.

Gerá-las a partir de UDF.

A qualquer momento, deve ser possível ver as tabelas e a projeção das quartas de final.

[1] O num_jogos_disputados é o número de jogos feitos por aquele time, até o presente instante. Jogos sem resultados não devem ser considerados.

[2] Saldo de gols é a diferença entre gols marcados e gols sofridos

[3] O total de pontos se dá somando os resultados, onde: (Vitória = 3 pontos, Empate = 1 ponto , Derrota = 0 pontos)
