package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Usuario usuario) {
		return "cliente/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("usuarios", service.buscarTodosClientes());
		return "cliente/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cliente/cadastro";
		}

		System.out.println("password = " + usuario.getSenha());
		
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		usuario.setPapel("CLIENTE");
		service.salvar(usuario);
		attr.addFlashAttribute("sucess", "cliente.insert.sucess");
		return "redirect:/clientes/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("usuario", service.buscarPorId(id));
		return "cliente/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "cliente/cadastro";
		}

		System.out.println(usuario.getSenha());
		
		service.salvar(usuario);
		attr.addFlashAttribute("sucess", "cliente.edit.sucess");
		return "redirect:/clientes/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("sucess", "cliente.delete.sucess");
		return listar(model);
	}
}