package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.service.spec.IPacoteService;

@RestController
public class PacoteRestController {

	@Autowired
	private IPacoteService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private void parse(Usuario agencia, JSONObject json) {

		Map<String, Object> map = (Map<String, Object>) json.get("agencia");

		Object id = map.get("id");
		if (id instanceof Integer) {
			agencia.setId(((Integer) id).longValue());
		} else {
			agencia.setId(((Long) id));
		}

		agencia.setCnpj((String) map.get("cnpj"));
		agencia.setNome((String) map.get("nome"));
	}

	private void parse(Pacote pacote, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				pacote.setId(((Integer) id).longValue());
			} else {
				pacote.setId(((Long) id));
			}
		}

		pacote.setNome((String) json.get("nome"));
		pacote.setCidade((String) json.get("cidade"));
		pacote.setEstado((String) json.get("estado"));
		pacote.setPais((String) json.get("pais"));

		Date partida = null;

		try {
			partida = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse((String) json.get("partida"))).getTime());
		} catch (Exception e) {
		}
	
       	pacote.setPartida(partida);
		pacote.setDuracao((Integer) json.get("duracao"));
		Double value = (Double) json.get("valor");
		pacote.setValor(BigDecimal.valueOf(value));

		Usuario agencia = new Usuario();
		parse(agencia, json);
		pacote.setAgencia(agencia);
	}

	@GetMapping(path = "/pacotes")
	public ResponseEntity<List<Pacote>> lista() {
		List<Pacote> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/pacotes/destinos/{cidade}")
	public ResponseEntity<List<Pacote>> listaPorDestino(@PathVariable("cidade") String cidade) {
		List<Pacote> lista = service.buscarPorCidade(cidade);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/pacotes/agencias/{id}")
	public ResponseEntity<List<Pacote>> listaPorAgencia(@PathVariable("id") long id) {
		List<Pacote> lista = service.buscarPorAgenciaById(id);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/pacotes/clientes/{id}")
	public ResponseEntity<List<Pacote>> listaPorCliente(@PathVariable("id") long id) {
		List<Pacote> lista = service.buscarPorClienteById(id);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	// @PostMapping(path = "/livros")
	// @ResponseBody
	// public ResponseEntity<Livro> cria(@RequestBody JSONObject json) {
	// 	try {
	// 		if (isJSONValid(json.toString())) {
	// 			Livro livro = new Livro();
	// 			parse(livro, json);
	// 			service.salvar(livro);
	// 			return ResponseEntity.ok(livro);
	// 		} else {
	// 			return ResponseEntity.badRequest().body(null);
	// 		}
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
	// 	}
	// }

	// @PutMapping(path = "/livros/{id}")
	// public ResponseEntity<Livro> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
	// 	try {
	// 		if (isJSONValid(json.toString())) {
	// 			Livro livro = service.buscarPorId(id);
	// 			if (livro == null) {
	// 				return ResponseEntity.notFound().build();
	// 			} else {
	// 				parse(livro, json);
	// 				service.salvar(livro);
	// 				return ResponseEntity.ok(livro);
	// 			}
	// 		} else {
	// 			return ResponseEntity.badRequest().body(null);
	// 		}
	// 	} catch (Exception e) {
	// 		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
	// 	}
	// }

	// @DeleteMapping(path = "/livros/{id}")
	// public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

	// 	Livro livro = service.buscarPorId(id);
	// 	if (livro == null) {
	// 		return ResponseEntity.notFound().build();
	// 	} else {
	// 		service.excluir(id);
	// 		return ResponseEntity.noContent().build();
	// 	}
	// }
}
