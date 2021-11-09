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

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.PacoteDAO;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/agencia/*")
public class AgenciaController extends HttpServlet {

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

		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!usuario.getPapel().equals("AGENCIA")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [AGENCIA] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
		}
		
		String action = request.getPathInfo();
		if (action == null) {
			action = "";
		}

		try {
			switch (action) {
				case "/cadastroPacote":
					apresentaFormularioCadastroPacote(request, response);
					break;
				case "/inserirPacote":
					inserePacote(request, response, usuario);
					break;
				case "/listaPacotesAgencia":
					listaPacotesAgencia(request, response, usuario);
					break;
				case "/atualizaPacote":
					apresentaFormularioEdicaoPacote(request, response);
					break;
				case "/atualizarPacote":
					atualizaPacote(request, response, usuario);
					break;
				case "/removePacote":
					removePacote(request, response, usuario);
					break;
				default:
					paginaInicial(request, response);
					break;
			}
		} catch (RuntimeException | IOException | ServletException e) {
			throw new ServletException(e);
		}
	}

	private void paginaInicial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/index.jsp");
		dispatcher.forward(request, response);
	}

	private void listaPacotesAgencia(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws ServletException, IOException {
		boolean vigentes = Boolean.parseBoolean(request.getParameter("vigentes"));
		if (vigentes) {
			List<Pacote> listaPacotes = pDao.getAllPacotesVigentesAgencia(usuario);
			request.setAttribute("lista", listaPacotes);
		} else {
			List<Pacote> listaPacotes = pDao.getAllPacotesAgencia(usuario);
			request.setAttribute("lista", listaPacotes);
		}
		
		request.setAttribute("vigentes", vigentes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/listaPacotes.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormularioCadastroPacote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormularioEdicaoPacote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Pacote pacote = pDao.get(id);
		request.setAttribute("pacote", pacote);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/agencia/formulario.jsp");
		dispatcher.forward(request, response);
	}

	private void inserePacote(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String nome = request.getParameter("nome");
		String cnpj = usuario.getCnpj();
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String pais = request.getParameter("pais");
		Date partida = null;

		try {
			partida = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("partida"))).getTime());
		} catch (Exception e) {
		}

		Integer duracao = Integer.parseInt(request.getParameter("duracao"));
		Float valor = Float.parseFloat(request.getParameter("valor"));

		Usuario agencia = uDao.getAgenciaByCnpj(cnpj);
			
		Pacote pacote = new Pacote(nome, agencia, cidade, estado, pais, partida, duracao, valor);

		pDao.insert(pacote);
		response.sendRedirect("listaPacotesAgencia");
	}

	private void atualizaPacote(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Long id = Long.parseLong(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String cnpj = usuario.getCnpj();
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String pais = request.getParameter("pais");
		Date partida = null;
		
		try {
			partida = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("partida"))).getTime());
		} catch (Exception e) {
		}
		
		Integer duracao = Integer.parseInt(request.getParameter("duracao"));
		Float valor = Float.parseFloat(request.getParameter("valor"));

		UsuarioDAO uDao = new UsuarioDAO();

		Usuario agencia = uDao.getAgenciaByCnpj(cnpj);
			
		Pacote pacote = new Pacote(id, nome, agencia, cidade, estado, pais, partida, duracao, valor);

		pDao.update(pacote);
		response.sendRedirect("listaPacotesAgencia");
	}

	private void removePacote(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Pacote pacote = new Pacote(id);                                                                           
		pDao.delete(pacote);
		response.sendRedirect("listaPacotesAgencia");
	}
}