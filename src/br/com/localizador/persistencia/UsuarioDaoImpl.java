package br.com.localizador.persistencia;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.localizador.domain.UsuarioJpaDao;
import br.com.localizador.model.Solicitante;
import br.com.localizador.model.Usuario;

@Repository
public class UsuarioDaoImpl extends AbstractJpaDao<Usuario> implements UsuarioJpaDao {
	
	public UsuarioDaoImpl(){
		setClazz(Usuario.class);
	}	
	
	@Override
	public Usuario login(String username, String password) throws Exception{
		TypedQuery<Usuario> query = entityManager.createQuery("from Usuario u where u.user = :username and u.password = :password", Usuario.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		Usuario usuario = query.getSingleResult();
		if (usuario == null ){
			throw new Exception("Usuário ou senha inválidos!");
		}
		return usuario;
	}

	@Override
	public Solicitante getSolicitante(Usuario usuario) throws Exception {
		TypedQuery<Solicitante> query = entityManager.createQuery("from Solicitante s where s.user = :username", Solicitante.class);
		query.setParameter("username", usuario.getUser());
		Solicitante s = query.getSingleResult();
		return s;
	}
	
}
