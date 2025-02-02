package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
	
	Usuario findById(long id);
	
	Usuario save(Usuario usuario);

	void deleteById(Long id);
	
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario getUserByUsername(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE u.papel = 'CLIENTE'")
	List<Usuario> findAllClientes();

	@Query("SELECT u FROM Usuario u WHERE u.papel = 'AGENCIA'")
	List<Usuario> findAllAgencias();
}