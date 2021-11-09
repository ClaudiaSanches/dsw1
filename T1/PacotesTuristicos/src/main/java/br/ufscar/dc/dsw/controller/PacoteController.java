package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.PacoteDAO;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/pacote/*")
public class PacoteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PacoteDAO dao;

	@Override
	public void init() {
		dao = new PacoteDAO();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		
		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}

		try {
			switch (action) {
				
				case "/listaPacotesCliente":
					listaPacotesCliente(request, response, usuario);
					break;
				default:
					listaDePacotes(request, response);
					break;
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}
	}

	private void listaDePacotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pacote> listaPacotes = dao.getAllPacotes();
		request.setAttribute("listaPacotes", listaPacotes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pacote/listaPacotes.jsp");
		dispatcher.forward(request, response);
	}

	private void listaPacotesCliente(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws ServletException, IOException {
		if (usuario.getPapel().equals("CLIENTE")) {
			List<Pacote> listaPacotes = dao.getAllPacotesCliente(usuario);
			request.setAttribute("listaPacotes", listaPacotes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pacote/listaPacotes.jsp");
			dispatcher.forward(request, response);
		} else {
			acessoNegadoCliente(request, response);
		}
	}

	private void acessoNegadoAgencia (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erro erros = new Erro();
		erros.add("Acesso não autorizado!");
		erros.add("Apenas usuários com papel [AGENCIA] têm acesso a essa página");
		request.setAttribute("mensagens", erros);
		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
		rd.forward(request, response);
	}

	private void acessoNegadoCliente (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erro erros = new Erro();
		erros.add("Acesso não autorizado!");
		erros.add("Apenas usuários com papel [CLIENTE] têm acesso a essa página");
		request.setAttribute("mensagens", erros);
		RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
		rd.forward(request, response);
	}
}