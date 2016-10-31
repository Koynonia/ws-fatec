/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import model.Grupo;
import model.Jogo;
import model.Resultado;
import model.Times;


public interface CampeonatoDAO {

	public void geraGrupos(int trigger) throws SQLException;
	public List<Grupo> consultaGrupos() throws SQLException;
	public void apagaGrupos() throws SQLException;
	public void geraJogos(Date dtInicio, int trigger) throws SQLException;
	public void atualizaJogos( List<Jogo> gols ) throws SQLException;
	public List<Jogo> consultaJogos() throws SQLException;
	public List<Jogo> consultaDataJogos(Date data) throws SQLException;
	public void apagaJogos() throws SQLException;
	public List<Resultado> resultadoGrupo(String grupo) throws SQLException;
	public List<Resultado> resultadoGeral() throws SQLException;
	public List<Times> quartasdeFinal() throws SQLException;
	public Times time(int codigoTime) throws SQLException;
}