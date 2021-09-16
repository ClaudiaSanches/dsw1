package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = { "/log.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Erro erros = new Erro();
		System.out.println("Uhul! Estamos no IndexController");
		if (request.getParameter("bOK") != null) {
			System.out.println("bOK");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			System.out.println("Login: " + login);
			System.out.println("Senha: " + senha);
			if (login == null || login.isEmpty()) {
				erros.add("Login não informado!");
			}
			if (senha == null || senha.isEmpty()) {
				erros.add("Senha não informada!");
			}
			if (!erros.isExisteErros()) {
				UsuarioDAO dao = new UsuarioDAO();
				Usuario usuario = dao.getbyLogin(login);
				if (usuario != null) {
					if (usuario.getSenha().equalsIgnoreCase(senha)) {
						System.out.println("Achou o usuario");
						request.getSession().setAttribute("usuarioLogado", usuario);
						if (usuario.getPapel().equals("ADMIN")) {
							response.sendRedirect("admin/");
						} else if (usuario.getPapel().equals("CLIENTE")) {
							response.sendRedirect("cliente/");
						} else if (usuario.getPapel().equals("AGENCIA")) {
							response.sendRedirect("agencia/");
						} else {
							// TODO
							// response.sendRedirect("compras/");
						}
						return;
					} else {
						erros.add("Senha inválida!");
					}
				} else {
					erros.add("Usuário não encontrado!");
				}
			}
		}
		// request.getSession().invalidate();

		// request.setAttribute("mensagens", erros);

		// String URL = "/login.jsp";
		// RequestDispatcher rd = request.getRequestDispatcher(URL);
		// rd.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}