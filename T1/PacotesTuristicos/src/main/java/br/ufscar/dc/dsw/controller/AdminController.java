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
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/admin/*")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;

	@Override
	public void init() {
		dao = new UsuarioDAO();
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
		} else if (!usuario.getPapel().equals("ADMIN")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
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
				case "/cadastroCliente":
					apresentaFormularioCadastroCliente(request, response);
					break;
				case "/inserirCliente":
					insereCliente(request, response);
					break;
				case "/cadastroAgencia":
					apresentaFormularioCadastroAgencia(request, response);
					break;
				case "/inserirAgencia":
					insereAgencia(request, response);
					break;
				case "/listaClientes":
					listaClientes(request, response);
					break;
				case "/listaAgencias":
					listaAgencias(request, response);
					break;
				case "/atualizaCliente":
					apresentaFormularioEdicaoCliente(request, response);
					break;
				case "/atualizarCliente":
					atualizaCliente(request, response);
					break;
				case "/atualizaAgencia":
					apresentaFormularioEdicaoAgencia(request, response);
					break;
				case "/atualizarAgencia":
					atualizaAgencia(request, response);
					break;
				case "/removeCliente":
					removeCliente(request, response);
					break;
				case "/removeAgencia":
					removeAgencia(request, response);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/index.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormularioCadastroCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formulario.jsp");
		request.setAttribute("usuario", "cliente");
		dispatcher.forward(request, response);
	}

	private void insereCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String papel = "CLIENTE";
		String cpf = request.getParameter("cpf");
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
		Date nasc = null;

		try {
			nasc = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("nasc"))).getTime());
		} catch (Exception e) {
		}

		Usuario cliente = new Usuario(email, senha, nome, papel, cpf, telefone, sexo, nasc);

		dao.insertCliente(cliente);
		response.sendRedirect("listaClientes");
	}

	private void apresentaFormularioCadastroAgencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formulario.jsp");
		request.setAttribute("usuario", "agencia");
		dispatcher.forward(request, response);
	}

	private void insereAgencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String papel = "AGENCIA";
		String cnpj = request.getParameter("cnpj");
		String descricao = request.getParameter("descricao");
		
		Usuario agencia = new Usuario(email, senha, nome, papel, cnpj, descricao);

		dao.insertAgencia(agencia);
		response.sendRedirect("listaAgencias");
	}

	private void listaClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> listaClientes = dao.getAllClientes();
		request.setAttribute("listaClientes", listaClientes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/listaClientes.jsp");
		dispatcher.forward(request, response);
	}

	private void listaAgencias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> listaAgencias = dao.getAllAgencias();
		request.setAttribute("listaAgencias", listaAgencias);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/listaAgencias.jsp");
		dispatcher.forward(request, response);
	}

	private void apresentaFormularioEdicaoCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario cliente = dao.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formulario.jsp");
		request.setAttribute("cliente", cliente);
		dispatcher.forward(request, response);
	}

	private void atualizaCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String papel = "CLIENTE";
		String cpf = request.getParameter("cpf");
		String telefone = request.getParameter("telefone");
		String sexo = request.getParameter("sexo");
		Date nasc = null;

		try {
			nasc = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("nasc"))).getTime());
		} catch (Exception e) {
		}
		
		Usuario cliente = new Usuario(id, email, senha, nome, papel, cpf, telefone, sexo, nasc);

		dao.updateCliente(cliente);
		response.sendRedirect("listaClientes");
	}

	private void apresentaFormularioEdicaoAgencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario agencia = dao.get(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/admin/formulario.jsp");
		request.setAttribute("agencia", agencia);
		dispatcher.forward(request, response);
	}

	private void atualizaAgencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Long id = Long.parseLong(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String papel = "AGENCIA";
		String cnpj = request.getParameter("cnpj");
		String descricao = request.getParameter("descricao");
		
		Usuario agencia = new Usuario(id, email, senha, nome, papel, cnpj, descricao);

		dao.updateAgencia(agencia);
		response.sendRedirect("listaAgencias");
	}

	private void removeCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario cliente = new Usuario(id);
		dao.delete(cliente);
		response.sendRedirect("listaClientes");
	}

	private void removeAgencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Usuario agencia = new Usuario(id);
		dao.delete(agencia);
		response.sendRedirect("listaAgencias");
	}
}