package br.com.localizador.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.ResponseList;
import facebook4j.conf.ConfigurationBuilder;
import br.com.localizador.domain.UsuarioJpaDao;
import br.com.localizador.model.FacebookUser;
import br.com.localizador.model.Solicitante;
import br.com.localizador.model.Usuario;


@Controller
public class UsuarioController {
	@Autowired
	private UsuarioJpaDao usuario;

	@RequestMapping(value = "/amigos/")
	public ModelAndView dadosFacebook(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("amigos");
		ResponseList<Friend> amigos = (ResponseList<Friend>) request.getSession().getAttribute("amigos");
		
		model.addObject("amigos", amigos);

		return model;
	}	

	@RequestMapping(value = "/principal/")
	public ModelAndView principal(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("principal");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		model.addObject("usuario", usuario);

		if (!usuario.getFacebookId().isEmpty()){
			String accessToken = (String) request.getSession().getAttribute("accessToken");
			
			try {
				ConfigurationBuilder cb = new ConfigurationBuilder();
				cb.setDebugEnabled(true)
				  .setOAuthAppId("1035110476503940")
				  .setOAuthAppSecret("bc2396e10d12f4cc7f996160b34bbe2b")
				  .setOAuthPermissions("email, public_profile, user_friends")
				  .setOAuthAccessToken(accessToken);  
				FacebookFactory ff = new FacebookFactory(cb.build());
				Facebook facebook = ff.getInstance();
				ResponseList<Friend> results = facebook.getFriends();
				request.getSession().setAttribute("amigos", results);
				model.addObject("facebookFriends", results);		

			} catch (FacebookException e) {
				e.printStackTrace();
			} 
		}

		return model;
	}	

	@RequestMapping(value = "/usuario/salvar/")
	public ModelAndView salvar(@ModelAttribute Usuario user, HttpServletRequest request) {
		
		// Salva usuario
		try {
			usuario.save(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("usuario", user);
		return new ModelAndView("redirect:/principal/");
	}
	
	@RequestMapping(value = "/historico/")
	public ModelAndView historico(Model model, HttpServletRequest request) {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		ModelAndView modelView = new ModelAndView("historico");

		try {
			Solicitante s = usuario.getSolicitante(user);
			modelView.addObject("historico", s.getHistorico());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return modelView;
	}
	
	
	@RequestMapping(value = "/usuario/atualizar/")
	public ModelAndView atualizar(@ModelAttribute Usuario user, HttpServletRequest request) {
		
		// Salva usuario
		try {
			usuario.update(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("usuario", user);
		return new ModelAndView("redirect:/principal/");
	}
	
	//Método que exclui
	@RequestMapping(value = "/usuario/deletar/")
	 public ModelAndView deletar(@ModelAttribute Usuario user, HttpServletRequest request) {
				try {
					usuario.delete(user);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.getSession().setAttribute("usuario", user);
				return new ModelAndView("redirect:/principal/");
		
	 }
}
