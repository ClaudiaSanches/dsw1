package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;

import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.domain.Usuario;

public class PacoteDAO extends GenericDAO {
	/* Metodos CRUD para os pacotes turisticos */

	/* 
		C -> create 
	   	Insere novos pacotes turisticos no banco de dados
	*/
	public void insert(Pacote pacote) {

        String sql = "INSERT INTO Pacote (nome, cnpj, cidade, estado, pais, partida, duracao, valor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, pacote.getNome());
            statement.setString(2, pacote.getAgencia().getCnpj());
            statement.setString(3, pacote.getCidade());
            statement.setString(4, pacote.getEstado());
            statement.setString(5, pacote.getPais());
            statement.setDate(6, pacote.getPartida());
            statement.setInt(7, pacote.getDuracao());
            statement.setFloat(8, pacote.getValor());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* 
    	R -> read 
	  	Lista os pacotes turisticos do banco de dados
	*/
    public List<Pacote> getAllPacotes() {

        List<Pacote> lista = new ArrayList<>();

        String sql = "SELECT * from Pacote p, Usuario u WHERE p.cnpj = u.cnpj";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Date partida = resultSet.getDate("partida");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                long agencia_id = resultSet.getLong(10);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String agencia_nome = resultSet.getString(13);
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(agencia_id, email, senha, agencia_nome, papel, cnpj, descricao);
                Pacote pacote = new Pacote(id, nome, agencia, cidade, estado, pais, partida, duracao, valor);
                lista.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    // Retorna todos os pacotes de uma agencia, dado o usuario logado
    public List<Pacote> getAllPacotesAgencia(Usuario usuario) {

        List<Pacote> lista = new ArrayList<>();

        String sql = "SELECT * from Pacote p, Usuario u WHERE u.id = ? AND p.cnpj = u.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            Long id = usuario.getId();

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long pacote_id = resultSet.getLong(1);
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Date partida = resultSet.getDate("partida");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String agencia_nome = resultSet.getString(13);
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(id, email, senha, agencia_nome, papel, cnpj, descricao);
                Pacote pacote = new Pacote(pacote_id, nome, agencia, cidade, estado, pais, partida, duracao, valor);
                lista.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    // Retorna todos os pacotes, filtrando pelo nome da agencia
    public List<Pacote> getAllPacotesPorAgencia(String agencia_nome) {

        List<Pacote> lista = new ArrayList<>();

        String sql = "SELECT * from Pacote p, Usuario u WHERE u.nome = ? AND p.cnpj = u.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agencia_nome);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Date partida = resultSet.getDate("partida");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                long agencia_id = resultSet.getLong(10);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(agencia_id, email, senha, agencia_nome, papel, cnpj, descricao);
                Pacote pacote = new Pacote(id, nome, agencia, cidade, estado, pais, partida, duracao, valor);
                lista.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    // Retorna todos os pacotes, filtrando pelo destino
    public List<Pacote> getAllPacotesPorDestino(String destino) {
        Pacote pacote = null;

        List<Pacote> lista = new ArrayList<>();

        String[] destinoString = destino.split(","); 
        String cidade = destinoString[0];
        if (cidade.contains("-")) {
            cidade = cidade.split(" -")[0];
        }

        String sql = "SELECT * from Pacote p, Usuario u WHERE p.cidade = ? AND p.cnpj = u.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cidade);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Date partida = resultSet.getDate("partida");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                long agencia_id = resultSet.getLong(10);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String agencia_nome = resultSet.getString(13);
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(agencia_id, email, senha, agencia_nome, papel, cnpj, descricao);
                pacote = new Pacote(id, nome, agencia, cidade, estado, pais, partida, duracao, valor);
                lista.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Pacote> getAllPacotesPorPartida(String partida) {

        List<Pacote> lista = new ArrayList<>();

        String sql = "SELECT * from Pacote p, Usuario u WHERE p.partida = ? AND p.cnpj = u.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            Date data = null;

            try {
                data = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(partida).getTime());
            } catch (Exception e) {
            }

            statement.setDate(1, data);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                long agencia_id = resultSet.getLong(10);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String agencia_nome = resultSet.getString(13);
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(agencia_id, email, senha, agencia_nome, papel, cnpj, descricao);
                Pacote pacote = new Pacote(id, nome, agencia, cidade, estado, pais, data, duracao, valor);
                lista.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Pacote> getAllPacotesCliente(Usuario usuario) {
        List<Pacote> lista = null;

        return lista;
    }

    /* 
		U -> update
	   	Atualiza pacotes turisticos no banco de dados
	*/
    public void update(Pacote pacote) {
        String sql = "UPDATE Pacote SET nome = ?, cnpj = ?, cidade = ?, estado = ?, pais = ?, partida = ?, duracao = ?, valor = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, pacote.getNome());
            statement.setString(2, pacote.getAgencia().getCnpj());
            statement.setString(3, pacote.getCidade());
            statement.setString(4, pacote.getEstado());
            statement.setString(5, pacote.getPais());
            statement.setDate(6, pacote.getPartida());
            statement.setInt(7, pacote.getDuracao());
            statement.setFloat(8, pacote.getValor());
            statement.setLong(9, pacote.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* 
		D -> delete
	   	Remove pacotes turisticos do banco de dados
	*/
    public void delete(Pacote pacote) {
        String sql = "DELETE FROM Pacote where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, pacote.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    /* Procura pacotes turisticos pelo id */
    public Pacote get(Long id) {
        Pacote pacote = null;

        String sql = "SELECT * from Pacote p, Usuario u WHERE p.id = ? AND p.cnpj = u.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Date partida = resultSet.getDate("partida");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                long agencia_id = resultSet.getLong(10);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String agencia_nome = resultSet.getString(13);
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(agencia_id, email, senha, agencia_nome, papel, cnpj, descricao);
                pacote = new Pacote(id, nome, agencia, cidade, estado, pais, partida, duracao, valor);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacote;
    }

    public List<String> getAllDestinos() {

        List<String> lista = new ArrayList<>();

        String sql = "SELECT cidade, estado, pais from Pacote";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                String destino = null;
                if (estado == null) {
                    destino = cidade + " - " + pais;
                } else {
                    destino = cidade + ", " + estado + " - " + pais;
                }
                lista.add(destino);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<String> getAllPartidas() {

        List<String> lista = new ArrayList<>();

        String sql = "SELECT partida from Pacote";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                lista.add(resultSet.getString("partida"));
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Pacote> getPacotesPorAgencia(String agencia_nome) {
        System.out.println("Agência: " + agencia_nome);
        Pacote pacote = null;

        List<Pacote> lista = new ArrayList<>();

        String sql = "SELECT * from Pacote p, Usuario u WHERE u.nome = ? AND p.cnpj = u.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, agencia_nome);

            System.out.println("sql query: " + sql);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Date partida = resultSet.getDate("partida");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                long agencia_id = resultSet.getLong(10);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(agencia_id, email, senha, agencia_nome, papel, cnpj, descricao);
                pacote = new Pacote(id, nome, agencia, cidade, estado, pais, partida, duracao, valor);
                lista.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Pacote> getPacotesPorDestino(String destino) {
        Pacote pacote = null;

        List<Pacote> lista = new ArrayList<>();

        String[] destinoString = destino.split(" "); 
        String cidade = destinoString[0];
        String estado = destinoString[2];
        String pais = destinoString[4];

        String sql = "SELECT * from Pacote p, Usuario u WHERE p.cidade = ? AND p.estado = ? AND p.pais = ? AND p.cnpj = u.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cidade);
            statement.setString(2, estado);
            statement.setString(3, pais);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                Date partida = resultSet.getDate("partida");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                long agencia_id = resultSet.getLong(7);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String agencia_nome = resultSet.getString(10);
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(agencia_id, email, senha, agencia_nome, papel, cnpj, descricao);
                pacote = new Pacote(id, nome, agencia, cidade, estado, pais, partida, duracao, valor);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Pacote> getPacotesPorPartida(String partida) {
        Pacote pacote = null;

        List<Pacote> lista = new ArrayList<>();

        String sql = "SELECT * from Pacote p WHERE p.partida = ? AND p.cnpj = u.cnpj";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, partida);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Date partidaData = resultSet.getDate("partida");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                long agencia_id = resultSet.getLong(10);
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String agencia_nome = resultSet.getString(13);
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(agencia_id, email, senha, agencia_nome, papel, cnpj, descricao);
                pacote = new Pacote(id, nome, agencia, cidade, estado, pais, partidaData, duracao, valor);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Pacote> getAllPacotesVigentesAgencia(Usuario usuario) {

        List<Pacote> lista = new ArrayList<>();

        Date data = new java.sql.Date(System.currentTimeMillis());  

        String sql = "SELECT * from Pacote p, Usuario u WHERE u.id = ? AND p.cnpj = u.cnpj AND p.partida > ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            Long id = usuario.getId();

            statement.setLong(1, id);
            statement.setDate(2, data);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long pacote_id = resultSet.getLong(1);
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String pais = resultSet.getString("pais");
                Date partida = resultSet.getDate("partida");
                Integer duracao = resultSet.getInt("duracao");
                Float valor = resultSet.getFloat("valor");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String agencia_nome = resultSet.getString(13);
                String papel = resultSet.getString("papel");
                String descricao = resultSet.getString("descricao");
                Usuario agencia = new Usuario(id, email, senha, agencia_nome, papel, cnpj, descricao);
                Pacote pacote = new Pacote(pacote_id, nome, agencia, cidade, estado, pais, partida, duracao, valor);
                lista.add(pacote);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}