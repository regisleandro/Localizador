package br.com.localizador.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Historico {
	@Id
	@GeneratedValue
	private Integer id;
	private Date data;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Solicitado solicitado;

	public Solicitado getSolicitado() {
		return solicitado;
	}

	public void setSolicitado(Solicitado solicitado) {
		this.solicitado = solicitado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void salvar(){
		
	}
}
