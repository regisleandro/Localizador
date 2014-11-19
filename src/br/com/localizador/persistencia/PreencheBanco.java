package br.com.localizador.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.localizador.model.Usuario;

public class PreencheBanco {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.createQuery("delete from Usuario");
		
		Usuario usuario = new Usuario();
		
		usuario.setUser("Sabrina");
		usuario.setPassword("123");
		
		entityManager.persist(usuario);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();

	}

}