package edu.curso.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.curso.dao.TimeDAO;
import edu.curso.dao.TimeDAOImpl;
import edu.curso.entidade.Time;

/**
 * Servlet implementation class TimeController
 */
@WebServlet("/TimeController")
public class TimeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtId = request.getParameter("txtId");
		String txtNome = request.getParameter("txtNome");
		String txtModalidade = request.getParameter("txtModalidade");
		String txtMascote = request.getParameter("txtMascote");
		String cmd = request.getParameter("cmd");
		
		TimeDAO tdao = new TimeDAOImpl();
		
		if ("Adicionar".equals(cmd)) { 
			Time t = new Time();
			t.setId( Long.parseLong(txtId) );
			t.setNome( txtNome );
			t.setModalidade(txtModalidade);
			t.setMascote(txtMascote);
			tdao.adicionar(t);
		} else if ("Pesquisar".equals(cmd)) { 
			List<Time> times = tdao.pesquisarPorNome( txtNome );
			HttpSession session = request.getSession();
			session.setAttribute("MENSAGEM", "Foram encontrados : " + times.size() + " times");
			session.setAttribute("TIMES", times);
		}
		
		response.sendRedirect("./time.jsp");
	}

}
