package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.ufscar.dc.dsw.domain.Pacote;

@SuppressWarnings("unchecked")
public interface IPacoteDAO extends CrudRepository<Pacote, Long>{

	Pacote findById(long id);

	List<Pacote> findByCidade(String cidade);

	@Query("SELECT p FROM Pacote p where p.agencia.id = :id")
	List<Pacote> findAllByAgenciaById(@Param("id") long id);

	@Query("SELECT p FROM Pacote p, Compra c where c.usuario.id = :id and c.pacote.id = p.id")
	List<Pacote> findAllByClienteById(@Param("id") long id);

	List<Pacote> findAll();
	
	Pacote save(Pacote pacote);

	void deleteById(Long id);
}