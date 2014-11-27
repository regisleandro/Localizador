package br.com.localizador.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Solicitante extends Usuario{

	private static final long serialVersionUID = 539643075721225491L;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "HISTORICO_ID", nullable = false)
	private List<Historico> historico;
	
	public Solicitante(){
		
	}
	
	public Solicitante(Usuario u) {
		super.setFacebookId(u.getFacebookId());
		super.setUser(u.getUser());
	}

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
