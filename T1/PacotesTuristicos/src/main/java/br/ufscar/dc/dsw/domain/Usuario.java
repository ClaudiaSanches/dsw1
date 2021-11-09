package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class  Usuario {

	// Atributos comuns a todos os usuarios
	private Long id;
	private String email;
	private String senha;
	private String nome;
	private String papel;

	// Atributos exclusivos de usuarios do tipo CLIENTE
	private String cpf;
	private String telefone;
	private String sexo;
	private Date nasc;

	// Atributos exclusivos de usuarios do tipo AGENCIA
	private String cnpj;
	private String descricao;

	public Usuario(Long id) {
		this.id = id;
	}

	/* Construtores ADMIN */
	public Usuario(String email, String senha, String nome, String papel) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.papel = papel;
	}

	public Usuario(Long id, String email, String senha, String nome, String papel) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.papel = papel;
	}

	/* Construtores CLIENTE */
	public Usuario(String email, String senha, String nome, String papel, String cpf, String telefone, String sexo, Date nasc) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.papel = papel;
		this.cpf = cpf;
		this.telefone = telefone;
		this.sexo = sexo;
		this.nasc = nasc;
	}

	public Usuario(Long id, String email, String senha, String nome, String papel, String cpf, String telefone, String sexo, Date nasc) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.papel = papel;
		this.cpf = cpf;
		this.telefone = telefone;
		this.sexo = sexo;
		this.nasc = nasc;
	}

	/* Construtores AGENCIA */
	public Usuario(String email, String senha, String nome, String papel, String cnpj, String descricao) {
		super();
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.papel = papel;
		this.cnpj = cnpj;
		this.descricao = descricao;
	}

	public Usuario(Long id, String email, String senha, String nome, String papel, String cnpj, String descricao) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.papel = papel;
		this.cnpj = cnpj;
		this.descricao = descricao;
	}

	/* Getters e setters */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
}