package br.com.localizador.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 4309342529101025662L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
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
