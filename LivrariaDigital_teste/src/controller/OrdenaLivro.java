/**
 * @author Fernando Moraes Oliveira
 * Matéria 4724 - Engenharia de Software 3
 * 4º ADS - Noite
 * Iniciado em 29/04/2016
 */

package controller;


import java.util.Comparator;
import entity.Livro;;

public class OrdenaLivro implements Comparator<Livro> {

	@Override
	public int compare(Livro primeiro, Livro segundo) {

		return primeiro.getDtCadastro().compareTo(segundo.getDtCadastro());
	}
	
	public class OrdenaCadastro implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getIsbn().compareTo(segundo.getIsbn());
		}	
	}

	public class OrdenaTitulo implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getTitulo().compareTo(segundo.getTitulo());
		}
	}
	
	public class OrdenaAutor implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getAutor().compareTo(segundo.getAutor());
		}
	}
	
	public class OrdenaEditora implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getEditora().compareTo(segundo.getEditora());
		}
	}
	
	public class OrdenaPublicacao implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getDtPublicacao().compareTo(segundo.getDtPublicacao());
		}
	}
	
	public class OrdenaCapa implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getCapa().compareTo(segundo.getCapa());
		}
	}
	
	public class OrdenaPaginas implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return Integer.toString( primeiro.getPaginas() ).compareTo(Integer.toString( segundo.getPaginas() ));
		}
	}
	
	public class OrdenaCategoria implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return primeiro.getCategoria().compareTo(segundo.getCategoria());
		}
	}
	
	public class OrdenaPrecoCusto implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return Double.toString( primeiro.getPrecoCusto() ).compareTo(Double.toString( segundo.getPrecoCusto() ));
		}
	}
	
	public class OrdenaPrecoVenda implements Comparator<Livro> {

		@Override
		public int compare(Livro primeiro, Livro segundo) {

			return Double.toString( primeiro.getPrecoVenda() ).compareTo(Double.toString( segundo.getPrecoVenda() ));
		}
	}
}
