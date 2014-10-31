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
	
	@RequestMapping(value = "/cadastro/")
	public String iniciar(Model model) {
		model.addAttribute("action","/usuario/salvar/");
		model.addAttribute("usuario", new Usuario());
		return "cadastro";
	}

	@RequestMapping(value = "/dados/")
	public ModelAndView dadosFacebook(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("dados");
		model.addObject("facebookUser", (FacebookUser) request.getSession().getAttribute("facebookUser"));
		return model;
	}	
	
	@RequestMapping(value = "/principal/")
	public ModelAndView principal(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("principal");
		return model;
	}	

	// Método que salva
	@RequestMapping(value = "/usuario/salvar/")
	public String salvar(@ModelAttribute Usuario user, Model model) {
		
		// Salva usuario
		try {
			usuario.save(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cadastro";
	}
}
