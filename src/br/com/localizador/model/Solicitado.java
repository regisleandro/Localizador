package br.com.localizador.model;

public class Solicitado {

	private Integer id;
	private String nome;
	
	private Localizacao localizacao;	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}


	public void aceitarSolicitacao(){
		
	}
	
	public void enviarLocalizacao(){
		
	}
	
}
