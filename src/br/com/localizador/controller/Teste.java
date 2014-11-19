package br.com.localizador.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class Teste {

	@RequestMapping(value = "/teste/")
	public ModelAndView iniciar() {
		ModelAndView model = new ModelAndView("inicio");
		return model;
	}
	
}
