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
	public String geraJogos(Date dtInicio);
	public List<Grupo> consultaGrupos(String grupo) throws CampeonatoDAOException;
	public List<Jogo> consultaDataJogos(Date data) throws CampeonatoDAOException;
	public List<Jogo> consultaJogos() throws CampeonatoDAOException;
	
}
