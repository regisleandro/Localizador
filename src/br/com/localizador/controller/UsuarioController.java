package br.com.localizador.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.ResponseList;
import facebook4j.conf.ConfigurationBuilder;
import br.com.localizador.domain.UsuarioJpaDao;
import br.com.localizador.model.FacebookUser;
import br.com.localizador.model.Historico;
import br.com.localizador.model.Localizacao;
import br.com.localizador.model.Solicitado;
import br.com.localizador.model.Solicitante;
import br.com.localizador.model.Usuario;


@Controller
public class UsuarioController {
	@Autowired
	private UsuarioJpaDao usuarioDao;

	@RequestMapping(value = "/amigos/")
	public ModelAndView dadosFacebook(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("amigos");
		ResponseList<Friend> amigos = (ResponseList<Friend>) request.getSession().getAttribute("amigos");
		
		model.addObject("amigos", amigos);

		return model;
	}	
	
	
	@RequestMapping(value = "/principal/gravaHistorico")
	@ResponseBody
	public String dadosUsuario(HttpServletRequest request,  @RequestParam (value="posicao") String geoDados) {
		String[] dados = geoDados.split("\\|");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		try{
			String user = (dados[0]).split("\\:").length > 1 ? (dados[0]).split("\\:")[1] : dados[0] ;
	
			Usuario u = usuarioDao.getUsuario(user.replaceAll("^\\s+",""));	
			
			EntityManager entityManager = usuarioDao.getEntityManager();
			entityManager.getTransaction().begin();

			
			Solicitante s = usuarioDao.getSolicitante(usuario);
			if (s == null){
				s = new Solicitante(usuario);
			}

			Solicitado sol = new Solicitado();
			sol.setNome(u.getUser());
			Localizacao l = new Localizacao();
			l.setLatitude(dados[1]);
			l.setLongitude(dados[2]);
			entityManager.persist(l);
			
			List<Historico> hist = new ArrayList<Historico>();
			sol.setLocalizacao(l);
			entityManager.persist(sol);

			Historico h = new Historico();
			h.setData(DateTime.now().toDate());
			
			Localizacao l2 = new Localizacao();
			l2.setLatitude(dados[3]);
			l2.setLongitude(dados[4]);	
			entityManager.persist(l2);			

			h.setLocalizacao(l2);
			
			hist.add(h);
			
			s.setHistorico(hist);
			
			entityManager.merge(s);
			
			entityManager.getTransaction().commit();
			
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		
		
		
		return "OK";
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
			usuarioDao.save(user);
			
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
			Solicitante s = usuarioDao.getSolicitante(user);
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
			usuarioDao.update(user);
			
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
					usuarioDao.delete(user);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.getSession().setAttribute("usuario", user);
				return new ModelAndView("redirect:/principal/");
		
	 }
}
