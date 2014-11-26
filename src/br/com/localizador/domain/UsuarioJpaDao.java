package br.com.localizador.domain;

import br.com.localizador.model.Solicitante;
import br.com.localizador.model.Usuario;

public interface UsuarioJpaDao extends IGenericDao<Usuario> {
	public Usuario login(String username, String password) throws Exception;
	public Usuario loginFacebook(String facebookId)  throws Exception;
	public Solicitante getSolicitante(Usuario usuario) throws Exception;
}
