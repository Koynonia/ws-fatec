package test;

import model.Grupo;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOImpl;

public class UC01GerarGrupo {
	
	static Grupo grupo;
	static CampeonatoDAO dao;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new CampeonatoDAOImpl();
		dao.geraGrupos();
	}
	
	/**
	 * estabelece as pre-condicoes antes da execucao de cada teste
	 * @throws Exception
	 */
	@Before
	public void excluiGrupos() throws Exception{
		dao.apagaGrupos();
	}
	
	/**
	 * estabelece as pre-condicoes depois da execucao de cada teste
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao.apagaGrupos();
	}

	@Test
	public void CT01UC01GerarGrupo_sucesso() {
		dao.geraGrupos();
	}
}