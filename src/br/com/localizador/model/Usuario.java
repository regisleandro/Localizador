package br.com.localizador.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Usuario implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	private String user;
	private String password;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void cadastrar(){
		
	}

	public void alterar(){
		
	}
	
	public void excluir(){
		
	}
	
	public void consultar(){
		
	}
	
	
}
