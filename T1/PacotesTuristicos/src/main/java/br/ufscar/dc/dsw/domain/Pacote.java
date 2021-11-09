package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Pacote {

	private Long id;
	private String nome;
	private Usuario agencia;
	private String cidade;
	private String estado;
	private String pais;
	private Date partida;
	private Integer duracao;
	private Float valor;

	public Pacote(Long id) {
		this.id = id;
	}

	public Pacote(String nome, Usuario agencia, String cidade, String estado, String pais, Date partida, Integer duracao, Float valor) {
		super();
		this.nome = nome;
		this.agencia = agencia;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.partida = partida;
		this.duracao = duracao;
		this.valor = valor;
	}

	public Pacote(Long id, String nome, Usuario agencia, String cidade, String estado, String pais, Date partida, Integer duracao, Float valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.agencia = agencia;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.partida = partida;
		this.duracao = duracao;
		this.valor = valor;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getAgencia() {
		return agencia;
	}

	public void setAgencia(Usuario agencia) {
		this.agencia = agencia;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Date getPartida() {
		return partida;
	}

	public void setPartida(Date partida) {
		this.partida = partida;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
}