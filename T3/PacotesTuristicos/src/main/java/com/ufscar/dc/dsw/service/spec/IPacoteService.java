package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Pacote;

public interface IPacoteService {

	Pacote buscarPorId(Long id);

	List<Pacote> buscarPorCidade(String cidade);

	List<Pacote> buscarPorAgenciaById(Long id);

	List<Pacote> buscarPorClienteById(Long id);
	
	List<Pacote> buscarTodos();
	
	void salvar(Pacote pacote);
	
	void excluir(Long id);
}