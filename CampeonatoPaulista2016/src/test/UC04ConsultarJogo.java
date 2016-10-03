package test;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Random;

import model.Grupo;
import model.Jogo;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.CampeonatoDAO;
import persistence.CampeonatoDAOException;
import persistence.CampeonatoDAOImpl;

public class UC04ConsultarJogo {
	
	static List<Jogo> resultadoEsperado;
	static List<Jogo> resultadoObtido;
	static CampeonatoDAO dao;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new CampeonatoDAOImpl();
		dao.geraJogos( new Date() );
		resultadoEsperado = dao.consultaJogos();
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
	
	/**
	 * consulta com sucesso todos os Jogos Existentes
	 * @throws CampeonatoDAOException
	 */
	@Test
	public void CT01UC04ConsultarJogo_sucesso() throws CampeonatoDAOException {
		resultadoObtido = dao.consultaJogos();
		assertTrue(resultadoEsperado.equals( resultadoObtido ));
	}
	
	/**
	 * consulta com sucesso todos os Jogos Inexistentes
	 * @throws CampeonatoDAOException
	 */
	@Test
	public void CT02UC04ConsultarJogo_sucesso() throws CampeonatoDAOException {
		dao.apagaJogos();
		resultadoObtido = dao.consultaJogos();
		assertTrue(resultadoEsperado.equals( resultadoObtido ));
	}
	
	/**
	 * consulta com sucesso os Jogos Existentes por uma data
	 * @throws CampeonatoDAOException
	 */
	@Test
	public void CT03UC04ConsultarJogo_sucesso() throws CampeonatoDAOException {
		resultadoObtido = dao.consultaDataJogos( new Date() );
		assertTrue(resultadoEsperado.equals( resultadoObtido ));
	}
	
	/**
	 * consulta com sucesso os Jogos Existentes por uma data nula
	 * @throws CampeonatoDAOException
	 */
	@Test
	public void CT04UC04ConsultarJogo_sucesso() throws CampeonatoDAOException {
		resultadoObtido = dao.consultaDataJogos( null );
		assertTrue(resultadoEsperado.equals( resultadoObtido ));
	}
	
	/**
	 * consulta com sucesso os Jogos Existentes por uma data inexistente
	 * @throws CampeonatoDAOException
	 */
	@Test
	public void CT05UC04ConsultarJogo_sucesso() throws CampeonatoDAOException {
		Random rnd = new Random();
		long dt = -946771200000L + ( Math.abs( rnd.nextLong()) % ( 70L * 364 * 24 * 60 * 60 *1000));
		resultadoObtido = dao.consultaDataJogos( new Date( dt ) );
		assertTrue(resultadoEsperado.equals( resultadoObtido ));
	}
}