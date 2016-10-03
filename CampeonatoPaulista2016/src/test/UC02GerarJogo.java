package test;

import java.util.Date;

import model.Jogo;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOImpl;

public class UC02GerarJogo {
	
	static Jogo jogo;
	static CampeonatoDAO dao;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new CampeonatoDAOImpl();
		dao.geraJogos( new Date() );
	}
	
	/**
	 * estabelece as pre-condicoes antes da execucao de cada teste
	 * @throws Exception
	 */
	@Before
	public void excluiJogos() throws Exception{
		dao.apagaJogos();
	}
	
	/**
	 * estabelece as pre-condicoes depois da execucao de cada teste
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao.apagaJogos();
	}

	@Test
	public void CT01UC02GerarJogo_sucesso() {
		dao.geraJogos( new Date() );
	}
}