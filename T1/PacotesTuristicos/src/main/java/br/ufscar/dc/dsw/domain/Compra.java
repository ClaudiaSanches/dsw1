package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Compra {

	private Long id;
	private Date data;
	private Float valor;
	private Pacote pacote;
	private Usuario usuario;

	public Compra(Long id) {
		this.id = id;
	}

	public Compra(Date data, Float valor, Pacote pacote, Usuario usuario) {
		super();
		this.data = data;
		this.valor = valor;
		this.pacote = pacote;
		this.usuario = usuario;
	}

	public Compra(Long id, Date data, Float valor, Pacote pacote, Usuario usuario) {
		super();
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.pacote = pacote;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}