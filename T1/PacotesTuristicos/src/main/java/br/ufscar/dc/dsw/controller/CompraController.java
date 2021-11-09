package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.CompraDAO;
import br.ufscar.dc.dsw.dao.PacoteDAO;
import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.domain.Usuario;
// import br.ufscar.dc.dsw.util.EmailService;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/compras/*")
public class CompraController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CompraDAO dao;

    @Override
    public void init() {
        dao = new CompraDAO();
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
		} else if (!usuario.getPapel().equals("CLIENTE")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [CLIENTE] tem acesso a essa página");
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
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        List<Compra> listaCompras = dao.getAll(usuario);
        request.setAttribute("listaCompras", listaCompras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/compra/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, Pacote> getPacotes() {
        Map<Long, Pacote> pacotes = new HashMap<>();
        for (Pacote pacote : new PacoteDAO().getAllPacotes()) {
            pacotes.put(pacote.getId(), pacote);
        }
        return pacotes;
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pacotes", getPacotes());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/compra/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Long id = Long.parseLong(request.getParameter("pacote"));

        Pacote pacote = new PacoteDAO().get(id);
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        
        Date data = new java.sql.Date(System.currentTimeMillis());
        Compra compra = new Compra(data, pacote.getValor(), pacote, usuario);
        dao.insert(compra);

        // EmailService service = new EmailService();
        
        // InternetAddress from = new InternetAddress("dsw1.temp@gmail.com", "Suporte");
        // InternetAddress to_client = new InternetAddress(usuario.getEmail(), usuario.nome);
        // InternetAddress to_agency = new InternetAddress(pacote.agencia.getEmail(), pacote.agencia.nome);
                
        // String subject = "Compra de pacote turístico";
        // String body_client = "Olá! Você acabou de comprar o pacote " + pacote.nome + "!\nSua reunião para acertar os detalhes da compra está sendo marcada para amanhã, às 9:00. Caso esse horário não seja conveniente, entre em contato com a agência de turismo para agendar novo horário através do seguinte endereço de email: " + pacote.agencia.email + ".\n\nSegue link para chamada: https://meet.google.com/hef-odwk-swa.";
        // String body_agency = "Olá! O pacote " + pacote.nome + " acabou de ser comprado!\nSua reunião para acertar os detalhes da compra está sendo marcada para amnhã, às 9:00. Caso esse horário não seja conveniente, entre em contato com o cliente para agendar novo horário através do seguinte endereço de email: " + usuario.email + ".\n\nSegue link para chamada: https://meet.google.com/hef-odwk-swa.";
        // service.send(from, to_client, subject, body_client);
        // service.send(from, to_agency, subject, body_agency);
        
        response.sendRedirect("lista");
    }
}