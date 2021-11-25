package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "pacotes" })
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long> {
  
	@NotBlank
	@Email
    @Column(nullable = false, length = 64, unique = true)
    private String email;
    
	@NotBlank
    @Column(nullable = false, length = 64)
    private String senha;
       
    @NotBlank
    @Column(nullable = false, length = 64)
    private String nome;
    
    @Column(nullable = false, length = 16)
    private String papel;
    
    @Column(nullable = true, length = 32, unique = true)
    private String cpf;

    @Column(nullable = true, length = 16)
    private String telefone;

    @Column(nullable = true, length = 32)
    private String sexo;

    @Column(nullable = true)
    private Date nasc;

    @Column(nullable = true, length = 32, unique = true)
    private String cnpj;

    @Column(nullable = true, length = 256)
    private String descricao;

    @OneToMany(mappedBy = "agencia", cascade = CascadeType.ALL)
	private List<Pacote> pacotes;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getNasc() {
		return nasc;
	}

	public void setNasc(Date nasc) {
		this.nasc = nasc;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(List<Pacote> pacotes) {
		this.pacotes = pacotes;
	}
}