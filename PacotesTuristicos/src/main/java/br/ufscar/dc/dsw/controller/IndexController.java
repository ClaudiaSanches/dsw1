package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.PacoteDAO;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp" })
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UsuarioDAO uDao;
	private PacoteDAO pDao;

	@Override
	public void init() {
		uDao = new UsuarioDAO();
		pDao = new PacoteDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();
		
		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}

		try {
			switch (action) {
				default:
					paginaInicial(request, response);
					break;
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}
	}

	private void paginaInicial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pacote> listaPacotes = pDao.getAllPacotes();
		request.setAttribute("listaPacotes", listaPacotes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listaPacotes.jsp");
		dispatcher.forward(request, response);
	}
}
