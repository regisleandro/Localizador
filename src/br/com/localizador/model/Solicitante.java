package br.com.localizador.model;

import java.util.List;

public class Solicitante extends Usuario{
	
	// Lista do histórico referente ao solicitante
	private List<Historico> historico;
	
	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}

	public void enviarSolicitacao(){
		
	}
	
	public void listarHistorico(){
		
	}
	
	public void desenharTrajeto(){
		
	}
	
}
