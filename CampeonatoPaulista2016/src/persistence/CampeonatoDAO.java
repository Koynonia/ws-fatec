/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package persistence;

import java.util.Date;
import java.util.List;

import model.Grupo;
import model.Jogo;


public interface CampeonatoDAO {

	public String geraGrupos();
	public void apagaGrupos() throws CampeonatoDAOException;
	public List<Grupo> consultaGrupos() throws CampeonatoDAOException;
	public String geraJogos(Date dtInicio);	
	public List<Jogo> consultaJogos() throws CampeonatoDAOException;
	public void apagaJogos() throws CampeonatoDAOException;
	public List<Jogo> consultaDataJogos(Date data) throws CampeonatoDAOException;
}
