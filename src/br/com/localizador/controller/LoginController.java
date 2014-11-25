package br.com.localizador.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import br.com.localizador.domain.UsuarioJpaDao;
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
		String[] user = facebookUser.split("-");

		Usuario usuario = new Usuario();
		usuario.setFacebookId(user[0]);
		usuario.setUser(user[1]);
		
		String accessToken = user[2];
		request.getSession().setAttribute("usuario", usuario);
		request.getSession().setAttribute("accessToken", accessToken);
		return "OK";
	}
	
	@RequestMapping(value = "/inicio/novo_cadastro/")
	public ModelAndView novoCadastro(Model model,  HttpServletRequest request) {
		model.addAttribute("action","/usuario/salvar/");
		Usuario user = new Usuario();
		model.addAttribute("usuario", user);	
		
		return new ModelAndView("cadastro");	
	}		

	@RequestMapping(value = "/inicio/deletar/")
	public ModelAndView apagarCadastro(Model model,  HttpServletRequest request) {
		model.addAttribute("action","/usuario/deletar/");
		Usuario user = new Usuario();
		model.addAttribute("usuario", user);	
		
		return new ModelAndView("cadastro");	
	}	
	@RequestMapping(value = "/inicio/alterar_cadastro/")
	public ModelAndView alterarCadastro(Model model,  HttpServletRequest request) {
		model.addAttribute("action","/usuario/atualizar/");
		Usuario user = new Usuario();
		if (request.getSession().getAttribute("usuario")!=null){
			user = (Usuario) request.getSession().getAttribute("usuario");
		}
		model.addAttribute("usuario", user);	
		
		return new ModelAndView("alterar_cadastro");	
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
	@RequestMapping(value = "/sair/")
	public ModelAndView efetuarLogout(Model model, HttpServletRequest request) {
		request.getSession().invalidate();
		
		return new ModelAndView("redirect:/inicio/");
	}		
}
