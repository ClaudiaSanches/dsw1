/*
	Controla o fluxo de urls e de acesso ao banco de dados da página inicial
*/

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

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout/*", "/listaPacotes" })
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
				case "/logout":
					logout(request, response);
				default:
					paginaInicial(request, response);
					break;
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}
	}

	private void paginaInicial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pacote> listaPacotes = null;

		String agencia = request.getParameter("agencia");
		String destino = request.getParameter("destino");
		String partida = request.getParameter("partida");

		if (agencia != null) {
			listaPacotes = pDao.getAllPacotesPorAgencia(agencia);
		} else if (destino != null) {
			listaPacotes = pDao.getAllPacotesPorDestino(destino);
		} else if (partida != null) {
			listaPacotes = pDao.getAllPacotesPorPartida(partida);
		} else {
			listaPacotes = pDao.getAllPacotes();
		}
		
		List<String> listaAgencias = uDao.getAllAgenciasPeloNome();
		List<String> listaDestinos = pDao.getAllDestinos();
		List<String> listaPartidas = pDao.getAllPartidas();

		request.setAttribute("listaPacotes", listaPacotes);
		request.setAttribute("listaAgencias", listaAgencias);
		request.setAttribute("listaDestinos", listaDestinos);
		request.setAttribute("listaPartidas", listaPartidas);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/listaPacotes.jsp");
		dispatcher.forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("usuarioLogado");
		paginaInicial(request, response);
	}
}