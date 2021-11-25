package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IPacoteDAO;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.service.spec.IPacoteService;

@Service
@Transactional(readOnly = false)
public class PacoteService implements IPacoteService {

	@Autowired
	IPacoteDAO dao;
	
	public void salvar(Pacote pacote) {
		dao.save(pacote);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Pacote buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Pacote> buscarPorCidade(String cidade) {
		return dao.findByCidade(cidade);
	}

	@Transactional(readOnly = true)
	public List<Pacote> buscarPorAgenciaById(Long id) {
		return dao.findAllByAgenciaById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Pacote> buscarPorClienteById(Long id) {
		return dao.findAllByClienteById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Pacote> buscarTodos() {
		return dao.findAll();
	}
}