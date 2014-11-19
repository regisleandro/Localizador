package br.com.localizador.domain;

import br.com.localizador.model.Usuario;

public interface UsuarioJpaDao extends IGenericDao<Usuario> {
	public Usuario login(String username, String password) throws Exception;
}
