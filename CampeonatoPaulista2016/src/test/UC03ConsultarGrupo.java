package test;

import static org.junit.Assert.assertFalse;

import java.sql.SQLException;
import java.util.List;

import model.Grupo;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOImpl;

public class UC03ConsultarGrupo {
	
	static List<Grupo> resultadoEsperado;
	static List<Grupo> resultadoObtido;
	static CampeonatoDAO dao;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new CampeonatoDAOImpl();
		dao.geraGrupos(0);
		resultadoEsperado = dao.consultaGrupos();
	}
	
	/**
	 * estabelece as pre-condicoes antes da execucao de cada teste
	 * @throws Exception
	 */
	@Before
	public void tearDownBeforeClass() throws Exception{
		dao.geraGrupos(0);
	}
	
	/**
	 * estabelece as pre-condicoes depois da execucao de cada teste
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao.apagaGrupos();
	}
	
	/**
	 * consulta com sucesso os Grupos Existentes
	 * @throws SQLException 
	 */
	@Test
	public void CT01UC03ConsultarGrupo_sucesso() throws SQLException {
		resultadoObtido = dao.consultaGrupos();
		assertFalse(resultadoEsperado.equals( resultadoObtido ));
	}
	
	/**
	 * consulta com sucesso os Grupos Inesxistentes
	 * @throws SQLException 
	 */
	
	@Test ( expected = SQLException.class )
	public void CT02UC03ConsultarGrupo_sucesso() throws SQLException{
		dao.apagaGrupos();
		resultadoObtido = dao.consultaGrupos();
		assertFalse(resultadoEsperado.equals( resultadoObtido ));
	}
}