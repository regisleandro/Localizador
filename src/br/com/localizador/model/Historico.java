package br.com.localizador.model;

import java.util.Date;

public class Historico {

	private Date data;
	
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
