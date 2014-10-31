package br.com.localizador.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.localizador.domain.UsuarioJpaDao;
import br.com.localizador.model.FacebookUser;
import br.com.localizador.model.Usuario;

@Controller
public class LoginController {
	@Autowired
	private UsuarioJpaDao usuario;	

	@RequestMapping(value = "/inicio/")
	public ModelAndView iniciar() {
		ModelAndView model = new ModelAndView("inicio");
		return model;
	}
	
	@RequestMapping(value = "/inicio/loginFacebook")
	@ResponseBody
	public String dadosUsuario(HttpServletRequest request,  @RequestParam (value="json") String facebookUser) {
		System.out.println(facebookUser);
		String[] user = facebookUser.split("-");
		FacebookUser faceUser = new FacebookUser();
		faceUser.setId(user[0]);
		faceUser.setName(user[1]);
		request.getSession().setAttribute("facebookUser", faceUser);
		return "OK";
	}
	
	@RequestMapping(value = "/inicio/novo_cadastro/")
	public String principal(Model model) {
		model.addAttribute("action","/usuario/salvar/");
		model.addAttribute("usuario", new Usuario());
		return "cadastro";	
	}		

	@RequestMapping(value = "/inicio/login/")
	public ModelAndView efetuarLogin(Model model, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try{
		  Usuario user = usuario.login(username, password);
		  request.getSession().setAttribute("usuario", user);
		}catch(Exception ex){
			return new ModelAndView("redirect:/inicio/");
		}
		
		return new ModelAndView("redirect:/principal/");
	}		
}
