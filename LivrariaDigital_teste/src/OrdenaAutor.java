

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

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
	
	public void teste (){

//		Date[] datas = new Date[livros.size()];
//
//		String data = "";
//
//		for ( int i = 0; i < livros.size(); i++){
//			data = livros.get(i).getDtCadastro();
//			try {
//				datas[i] = formataData(data);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		Arrays.sort(datas);
//
//		for ( int i = 0; i < livros.size(); i++){
//			data = datas[i].toString();
//			System.out.println(data);
//  		System.out.println(formataData(data));

//		}
	}
	
	/**
	 * Converte uma String para um objeto Date. Caso a String seja vazia ou nula, 
	 * retorna null - para facilitar em casos onde formulários podem ter campos
	 * de datas vazios.
	 * @param data String no formato dd/MM/yyyy a ser formatada
	 * @return Date Objeto Date ou null caso receba uma String vazia ou nula
	 * @throws Exception Caso a String esteja no formato errado
	 */
	public static Date formataData(String data) throws Exception { 
		if (data == null || data.equals(""))
			return null;
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            date = (java.util.Date)formatter.parse(data);
        } catch (ParseException e) {            
            throw e;
        }
        return date;
	}
	
}
