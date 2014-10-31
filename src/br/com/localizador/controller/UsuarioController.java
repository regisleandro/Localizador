package br.com.localizador.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.localizador.domain.UsuarioJpaDao;
import br.com.localizador.model.FacebookUser;
import br.com.localizador.model.Usuario;


@Controller
public class UsuarioController {
	@Autowired
	private UsuarioJpaDao usuario;

	@RequestMapping(value = "/dados/")
	public ModelAndView dadosFacebook(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("dados");
		model.addObject("facebookUser", (FacebookUser) request.getSession().getAttribute("facebookUser"));
		return model;
	}	
	
	@RequestMapping(value = "/principal/")
	public ModelAndView principal(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("principal");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		model.addObject("usuario", usuario);
		return model;
	}	

	// Método que salva
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
}
