package br.ufscar.dc.dsw.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.List;

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

// import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

// @CrossOrigin
@RestController
public class ClienteRestController {

	@Autowired
 	private IUsuarioService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
 	}

	private void parse(Usuario cliente, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				cliente.setId(((Integer) id).longValue());
			} else {
				cliente.setId((Long) id);
			}
 	    	}

 	    	cliente.setEmail((String) json.get("email"));
 	    	cliente.setSenha((String) json.get("senha"));
        	cliente.setNome((String) json.get("nome"));
        	cliente.setPapel((String) json.get("papel"));
        	cliente.setCpf((String) json.get("cpf"));
        	cliente.setTelefone((String) json.get("telefone"));
        	cliente.setSexo((String) json.get("sexo"));

        	Date nasc = null;

		try {
			nasc = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse((String) json.get("nasc"))).getTime());
		} catch (Exception e) {
		}

        	cliente.setNasc(nasc);
 	}

	@GetMapping(path = "/clientes")
	public ResponseEntity<List<Usuario>> lista() {
		List<Usuario> lista = service.buscarTodosClientes();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 	}

	@GetMapping(path = "/clientes/{id}")
	public ResponseEntity<Usuario> lista(@PathVariable("id") long id) {
		Usuario cliente = service.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
 	}

	@PostMapping(path = "/clientes")
	@ResponseBody
	public ResponseEntity<Usuario> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Usuario cliente = new Usuario();
				parse(cliente, json);
				service.salvar(cliente);
				return ResponseEntity.ok(cliente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 	}

	@PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Usuario> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Usuario cliente = service.buscarPorId(id);
				if (cliente == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(cliente, json);
					service.salvar(cliente);
					return ResponseEntity.ok(cliente);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 	}

	@DeleteMapping(path = "/clientes/{id}")
 	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

        	Usuario cliente = service.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
    	}
}