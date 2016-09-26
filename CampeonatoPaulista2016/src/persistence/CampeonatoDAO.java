/**
 * @author Fernando Moraes Oliveira
 * Matéria Laboratório de Banco de Dados
 * 5º ADS - Tarde
 * Iniciado em 25/09/2016
 */

package persistence;

import java.util.List;

import model.Grupo;
import model.Jogo;


public interface CampeonatoDAO {

	public String geraGrupos();
	public String geraJogos();
	public List<Grupo> consultaGrupos(String grupo) throws CampeonatoDAOException;
	public List<Jogo> consultaJogos(String data) throws CampeonatoDAOException;
	
}
