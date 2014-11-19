package br.com.localizador.domain;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {	 
	    T findOne(final int id);
	    List<T> findAll();
	    void save(final T entity);
	    void update(final T entity);
	    void delete(final T entity);
	    void deleteById(final int entityId);
	}
