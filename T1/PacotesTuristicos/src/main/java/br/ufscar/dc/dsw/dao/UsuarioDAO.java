package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

	/* Metodos CRUD para todos os tipos de usuarios (ADMIN, CLIENTE e AGENCIA) */

	/* 
		C -> create 
	   	Insere novos usuarios no banco de dados
	*/
	public void insertAdmin(Usuario usuario) {

        String sql = "INSERT INTO Usuario (email, senha, nome, papel) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, "ADMIN");
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertCliente(Usuario usuario) {

        String sql = "INSERT INTO Usuario (email, senha, nome, papel, cpf, telefone, sexo, nasc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, "CLIENTE");
            statement.setString(5, usuario.getCpf());
            statement.setString(6, usuario.getTelefone());
            statement.setString(7, usuario.getSexo());
            statement.setDate(8, usuario.getNasc());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertAgencia(Usuario usuario) {

        String sql = "INSERT INTO Usuario (email, senha, nome, papel, cnpj, descricao) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, "AGENCIA");
            statement.setString(5, usuario.getCnpj());
            statement.setString(6, usuario.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* 
    	R -> read 
	  	Lista todos os usuarios do banco de dados
	*/
        public List<Usuario> getAllAdmins() {

        List<Usuario> listaAdmins = new ArrayList<>();

        String sql = "SELECT * from Usuario u WHERE papel = 'ADMIN'";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");
                Usuario admin = new Usuario(id, email, senha, nome, papel);
                listaAdmins.add(admin);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAdmins;
    }

    public List<Usuario> getAllClientes() {

        List<Usuario> listaClientes = new ArrayList<>();

        String sql = "SELECT * from Usuario u WHERE papel = 'CLIENTE'";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date nasc = resultSet.getDate("nasc");
                Usuario cliente = new Usuario(id, email, senha, nome, papel, cpf, telefone, sexo, nasc);
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public List<Usuario> getAllAgencias() {

        List<Usuario> listaAgencias = new ArrayList<>();

        String sql = "SELECT * from Usuario u WHERE papel = 'AGENCIA'";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");
                String cnpj = resultSet.getString("cnpj");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(id, email, senha, nome, papel, cnpj, descricao);
                listaAgencias.add(agencia);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgencias;
    }

    public List<String> getAllAgenciasPeloNome() {

        List<String> listaAgencias = new ArrayList<>();

        String sql = "SELECT nome from Usuario u WHERE papel = 'AGENCIA'";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listaAgencias.add(resultSet.getString("nome"));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaAgencias;
    }

    /* 
		U -> update
	   	Atualiza usuarios no banco de dados
	*/
    public void updateAdmin(Usuario usuario) {
        String sql = "UPDATE Usuario SET email = ?, senha = ?, nome = ?, papel = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getPapel());
            statement.setLong(5, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCliente(Usuario usuario) {
        String sql = "UPDATE Usuario SET email = ?, senha = ?, nome = ?, papel = ?, cpf = ?, telefone = ?, sexo = ?, nasc = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getPapel());
            statement.setString(5, usuario.getCpf());
            statement.setString(6, usuario.getTelefone());
            statement.setString(7, usuario.getSexo());
            statement.setDate(8, usuario.getNasc());
            statement.setLong(9, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAgencia(Usuario usuario) {
        String sql = "UPDATE Usuario SET email = ?, senha = ?, nome = ?, papel = ?, cnpj = ?, descricao = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getEmail());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getNome());
            statement.setString(4, usuario.getPapel());
            statement.setString(5, usuario.getCnpj());
            statement.setString(6, usuario.getDescricao());
            statement.setLong(7, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* 
		D -> delete
	   	Remove usuarios do banco de dados
	*/
    public void delete(Usuario usuario) {
        String sql = "DELETE FROM Usuario where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    /* Procura usuarios pelo id */
    public Usuario get(Long id) {
        Usuario usuario = null;

        String sql = "SELECT * from Usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");
                if (papel.equals("ADMIN")) {
                    usuario = new Usuario(id, email, senha, nome, papel);
                } else if (papel.equals("CLIENTE")) {
                    String cpf = resultSet.getString("cpf");
                    String telefone = resultSet.getString("telefone");
                    String sexo = resultSet.getString("sexo");
                    Date nasc = resultSet.getDate("nasc");
                    usuario = new Usuario(id, email, senha, nome, papel, cpf, telefone, sexo, nasc);
                } else {
                    String cnpj = resultSet.getString("cnpj");
                    String descricao = resultSet.getString("descricao");
                    usuario = new Usuario(id, email, senha, nome, papel, cnpj, descricao);
                }
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    
    /* Procura usuarios pelo login (email) */
    public Usuario getbyLogin(String email) {
        Usuario usuario = null;

        String sql = "SELECT * FROM Usuario WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");
                if (papel.equals("ADMIN")) {
                    usuario = new Usuario(id, email, senha, nome, papel);
                } else if (papel.equals("CLIENTE")) {
                    String cpf = resultSet.getString("cpf");
                    String telefone = resultSet.getString("telefone");
                    String sexo = resultSet.getString("sexo");
                    Date nasc = resultSet.getDate("nasc");
                    usuario = new Usuario(id, email, senha, nome, papel, cpf, telefone, sexo, nasc);
                } else {
                    String cnpj = resultSet.getString("cnpj");
                    String descricao = resultSet.getString("descricao");
                    usuario = new Usuario(id, email, senha, nome, papel, cnpj, descricao);
                }
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Usuario getAgenciaByCnpj(String cnpj) {
        Usuario agencia = null;

        String sql = "SELECT * FROM Usuario WHERE cnpj = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");

                agencia = new Usuario(id, email, senha, nome, papel, cnpj, descricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return agencia;
    }
}