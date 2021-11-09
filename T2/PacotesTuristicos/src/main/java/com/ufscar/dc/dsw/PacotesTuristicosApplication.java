package br.ufscar.dc.dsw;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.dao.IPacoteDAO;
import br.ufscar.dc.dsw.dao.ICompraDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.domain.Compra;

@SpringBootApplication
public class PacotesTuristicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacotesTuristicosApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IPacoteDAO pacoteDAO, ICompraDAO compraDAO) {
	// public CommandLineRunner demo(IUsuarioDAO usuarioDAO, IPacoteDAO pacoteDAO, ICompraDAO compraDAO) {
		return (args) -> {
			
			// Usuario admin = new Usuario();
			// admin.setEmail("adminnn@gmail.com");
			// admin.setSenha(encoder.encode("adminnn"));
			// admin.setNome("Adminnn");
			// admin.setPapel("ADMIN");
			// usuarioDAO.save(admin);

			
			// Usuario cliente = new Usuario();
			// cliente.setEmail("clienteee@gmail.com");
			// cliente.setSenha(encoder.encode("clienteee"));
			// cliente.setNome("Clienteee");
			// cliente.setPapel("CLIENTE");
			// cliente.setCpf("656");
			// cliente.setTelefone("47574");
			// cliente.setSexo("Masculino");

			// Date nasc = null;

			// try {
			// 	nasc = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse("1999-04-12")).getTime());
			// } catch (Exception e) {
			// }

			// cliente.setNasc(nasc);
			// usuarioDAO.save(cliente);
			
		// 	Usuario agencia = new Usuario();
		// 	agencia.setEmail("agenciaNova@gmail.com");
		// 	// agencia.setSenha(encoder.encode("agenciaNova"));
		// 	agencia.setSenha("agenciaNova");
		// 	agencia.setNome("Agencia Nova");
		// 	agencia.setPapel("AGENCIA");
		// 	agencia.setCnpj("354774");
		// 	agencia.setDescricao("Nova Agencia");
		// 	usuarioDAO.save(agencia);

		// 	Pacote pacote = new Pacote();
		// 	pacote.setNome("Pacote Novinho");
		// 	pacote.setAgencia(agencia);
		// 	pacote.setCidade("Amsterda");
		// 	pacote.setPais("Holanda");

		// 	Date partida = null;

		// 	try {
		// 		partida = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-02")).getTime());
		// 	} catch (Exception e) {
		// 	}

		// 	pacote.setPartida(partida);
		// 	pacote.setDuracao(2);
		// 	pacote.setValor(BigDecimal.valueOf(102.45));
		// 	pacoteDAO.save(pacote);

		// 	Compra compra = new Compra();
		// 	Date data = new java.sql.Date(System.currentTimeMillis());
		// 	compra.setData(data);
		// 	compra.setValor(BigDecimal.valueOf(102.45));
		// 	compra.setUsuario(cliente);
		// 	compra.setPacote(pacote);
		// 	compraDAO.save(compra);
		};
	}
}
