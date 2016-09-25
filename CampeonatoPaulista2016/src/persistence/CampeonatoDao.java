package persistence;

import java.util.List;

import model.Grupo;
import model.Jogo;


public interface CampeonatoDao {

	public String geraGrupos();
	public String geraJogos();
	public List<Grupo> consultaGrupos(String grupo) throws CampeonatoDAOException;
	public List<Jogo> consultaJogos(String data) throws CampeonatoDAOException;
	
}
