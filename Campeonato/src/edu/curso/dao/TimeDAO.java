package edu.curso.dao;

import java.util.List;

import edu.curso.entidade.Time;

public interface TimeDAO {

	public void adicionar(Time t);
	public List<Time> pesquisarPorNome(String nome);
}
