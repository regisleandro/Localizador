package br.com.localizador.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.localizador.model.FacebookUser;
import br.com.localizador.model.JsonResponse;

@Controller
public class LoginController {

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
}
