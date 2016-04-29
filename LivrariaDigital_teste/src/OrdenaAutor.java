

import java.util.Comparator;

import entity.Autor;

public class OrdenaAutor implements Comparator<Autor> {

	@Override
	public int compare(Autor um, Autor dois) {

		return um.getNome().compareTo(dois.getNome());
	}

	public class OrdenaPorNascimento implements Comparator<Autor> {

		@Override
		public int compare(Autor um, Autor dois) {

			return um.getDtNasc().compareTo(dois.getDtNasc());
		}
	}

	public class OrdenaPorFalecimento implements Comparator<Autor> {

		@Override
		public int compare(Autor um, Autor dois) {

			return um.getDtFalec().compareTo(dois.getDtFalec());
		}
	}
	
	public class OrdenaPorLocalNasc implements Comparator<Autor> {

		@Override
		public int compare(Autor um, Autor dois) {

			return um.getLocalNasc().compareTo(dois.getLocalNasc());
		}
	}
	
	public class OrdenaPorLocalFalec implements Comparator<Autor> {

		@Override
		public int compare(Autor um, Autor dois) {

			return um.getLocalFalec().compareTo(dois.getLocalFalec());
		}
	}

	public class OrdenaPorCadastro implements Comparator<Autor> {

		@Override
		public int compare(Autor um, Autor dois) {

			return um.getDtCadastro().compareTo(dois.getDtCadastro());
		}

	}
}
