package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Pacote")
public class Pacote extends AbstractEntity<Long> {
  
	@NotBlank
    @Column(nullable = false, length = 64, unique = true)
    private String nome;
    
	@ManyToOne
    @JoinColumn(name = "cnpj", referencedColumnName="cnpj")
    private Usuario agencia;
       
    @NotBlank
    @Column(nullable = false, length = 64)
    private String cidade;

    @Column(nullable = true, length = 64)
    private String estado;
    
    @NotBlank
    @Column(nullable = false, length = 64)
    private String pais;

    @NotNull
    @Column(nullable = false)
    private Date partida;

    @NotNull
    @Column(nullable = false)
    private Integer duracao;

    @NotNull
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
    private BigDecimal valor;
    
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}