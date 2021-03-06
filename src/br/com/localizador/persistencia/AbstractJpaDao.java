package br.com.localizador.persistencia;

// Classe gen�rica
// Faz a persist�ncia

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractJpaDao<T extends Serializable>{
	   private Class<T> clazz;
	   
	    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
	   public void setClazz(Class<T> clazzToSet){
	      this.clazz = clazzToSet;
	   }
	
	   public T findOne(int id){
		  entityManager.clear();
	      return entityManager.find(clazz, id);
	   }
	
	   public EntityManager getEntityManager(){
		   return entityManager;
	   }
	   
	   @SuppressWarnings("unchecked")
	   public List<T> findAll(){
		   entityManager.clear();
		   List<T> lista = Collections.EMPTY_LIST;
	      try{
	    	  if (entityManager != null)
	    		  lista = entityManager.createQuery("from " + clazz.getName()).getResultList();
	      }catch(Exception ex){
	    	  ex.printStackTrace();
	      }
	      return lista;
	   }
	 
	   public void save(T entity){
		  entityManager.getTransaction().begin();
	      entityManager.persist( entity );
	      entityManager.getTransaction().commit();
	   }
	 
	   public void update(T entity){
		  entityManager.getTransaction().begin();
	      entityManager.merge(entity);
	      entityManager.getTransaction().commit();
	   }
	 
	   public void delete(T entity){
		  entityManager.getTransaction().begin();
	      entityManager.remove( entity );
	      entityManager.getTransaction().commit();
	   }

	   public void deleteById(int entityId){
	      T entity = findOne(entityId);
	      delete( entity );
	   }

}

