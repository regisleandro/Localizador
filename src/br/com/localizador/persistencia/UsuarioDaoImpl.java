package br.com.localizador.persistencia;

import org.springframework.stereotype.Repository;

import br.com.localizador.domain.UsuarioJpaDao;
import br.com.localizador.model.Usuario;

@Repository
public class UsuarioDaoImpl extends AbstractJpaDao<Usuario> implements UsuarioJpaDao {
	
	public UsuarioDaoImpl(){
		setClazz(Usuario.class);
	}
	

}
