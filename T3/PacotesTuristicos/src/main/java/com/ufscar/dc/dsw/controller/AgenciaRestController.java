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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@RestController
public class AgenciaRestController {

	@Autowired
 	private IUsuarioService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
 	}

	private void parse(Usuario agencia, JSONObject json) {
		
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				agencia.setId(((Integer) id).longValue());
			} else {
				agencia.setId((Long) id);
			}
 	    	}

 	    	agencia.setEmail((String) json.get("email"));
 	    	agencia.setSenha((String) json.get("senha"));
        	agencia.setNome((String) json.get("nome"));
        	agencia.setPapel((String) json.get("papel"));
        	agencia.setCnpj((String) json.get("cnpj"));
        	agencia.setDescricao((String) json.get("descricao"));
 	}

	@GetMapping(path = "/agencias")
	public ResponseEntity<List<Usuario>> lista() {
		List<Usuario> lista = service.buscarTodasAgencias();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
 	}

	@GetMapping(path = "/agencias/{id}")
	public ResponseEntity<Usuario> lista(@PathVariable("id") long id) {
		Usuario agencia = service.buscarPorId(id);
		if (agencia == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(agencia);
 	}

	@PostMapping(path = "/agencias")
	@ResponseBody
	public ResponseEntity<Usuario> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Usuario agencia = new Usuario();
				parse(agencia, json);
				service.salvar(agencia);
				return ResponseEntity.ok(agencia);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 	}

	@PutMapping(path = "/agencias/{id}")
	public ResponseEntity<Usuario> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Usuario agencia = service.buscarPorId(id);
				if (agencia == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(agencia, json);
					service.salvar(agencia);
					return ResponseEntity.ok(agencia);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
 	}

	@DeleteMapping(path = "/agencias/{id}")
 	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

        	Usuario agencia = service.buscarPorId(id);
		if (agencia == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
    	}
}