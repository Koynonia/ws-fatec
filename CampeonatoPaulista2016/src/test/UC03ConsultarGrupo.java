package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import model.Grupo;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOException;
import persistence.CampeonatoDAOImpl;

public class UC03ConsultarGrupo {
	
	static List<Grupo> resultadoEsperado;
	static List<Grupo> resultadoObtido;
	static CampeonatoDAO dao;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new CampeonatoDAOImpl();
		dao.geraGrupos();
		resultadoEsperado = dao.consultaGrupos();
	}
	
	/**
	 * estabelece as pre-condicoes antes da execucao de cada teste
	 * @throws Exception
	 */
	@Before
	public void tearDownBeforeClass() throws Exception{
		dao.geraGrupos();
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
	 * @throws CampeonatoDAOException
	 */
	@Test
	public void CT01UC03ConsultarGrupo_sucesso() throws CampeonatoDAOException {
		resultadoObtido = dao.consultaGrupos();
		assertFalse(resultadoEsperado.equals( resultadoObtido ));
	}
	
	/**
	 * consulta com sucesso os Grupos Inesxistentes
	 * @throws CampeonatoDAOException
	 */
	
	@Test ( expected = CampeonatoDAOException.class )
	public void CT02UC03ConsultarGrupo_sucesso() throws CampeonatoDAOException {
		dao.apagaGrupos();
		resultadoObtido = dao.consultaGrupos();
		assertFalse(resultadoEsperado.equals( resultadoObtido ));
	}
}