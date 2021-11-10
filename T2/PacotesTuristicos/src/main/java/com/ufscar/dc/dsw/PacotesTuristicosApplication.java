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

		/*
			// Populando o banco de dados...
			
			// Administrador
			Usuario admin = new Usuario();
			admin.setEmail("admin2@gmail.com");
			admin.setSenha(encoder.encode("admin2"));
			admin.setNome("Admin2");
			admin.setPapel("ADMIN");
			usuarioDAO.save(admin);


			// Cliente
			Usuario cliente = new Usuario();
			cliente.setEmail("clienteNovo@gmail.com");
			cliente.setSenha(encoder.encode("clienteNovo"));
			cliente.setNome("Cliente Novo");
			cliente.setPapel("CLIENTE");
			cliente.setCpf("999999999-99");
			cliente.setTelefone("(22) 22222-2222");
			cliente.setSexo("Masculino");

			Date nasc = null;

			try {
				nasc = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse("1979-05-16")).getTime());
			} catch (Exception e) {
			}

			cliente.setNasc(nasc);
			usuarioDAO.save(cliente);


			// Agencia
			Usuario agencia = new Usuario();
			agencia.setEmail("agenciaNova@gmail.com");
			agencia.setSenha(encoder.encode("agenciaNova"));
			agencia.setNome("Agencia Nova");
			agencia.setPapel("AGENCIA");
			agencia.setCnpj("22.666.111/0001-88");
			agencia.setDescricao("Agencia Nova");
			usuarioDAO.save(agencia);

			
			// Pacote turístico
			Pacote pacote = new Pacote();
			pacote.setNome("Pacote Novo");
			pacote.setAgencia(agencia);
			pacote.setCidade("Amsterda");
			pacote.setPais("Holanda");

			Date partida = null;

			try {
				partida = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse("2021-10-12")).getTime());
			} catch (Exception e) {
			}

			pacote.setPartida(partida);
			pacote.setDuracao(12);
			pacote.setValor(BigDecimal.valueOf(1463.89));
			pacoteDAO.save(pacote);


			// Compra
			Compra compra = new Compra();
			Date data = new java.sql.Date(System.currentTimeMillis());
			compra.setData(data);
			compra.setValor(BigDecimal.valueOf(1463.89));
			compra.setUsuario(cliente);
			compra.setPacote(pacote);
			compraDAO.save(compra);
		*/
		};
	}
}
