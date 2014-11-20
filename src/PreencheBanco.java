import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.joda.time.DateTime;

import br.com.localizador.model.Historico;
import br.com.localizador.model.Localizacao;
import br.com.localizador.model.Solicitado;
import br.com.localizador.model.Solicitante;
import br.com.localizador.model.Usuario;


public class PreencheBanco {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
	//	Solicitante s1 = (Solicitante) entityManager.createQuery("from Solicitante ").getResultList().get(0);
		
		Usuario u = (Usuario) entityManager.createQuery("from Usuario ").getResultList().get(0);	
		System.out.println(u.getUser());
		
		Solicitante s = new Solicitante(u);
		
		List<Historico> hist = new ArrayList<Historico>();
		
		for(int i = 0; i < 5; i++){
			Solicitado solicitado = new Solicitado();
			solicitado.setNome("Regis");
			Localizacao l = new Localizacao();
			l.setLatitude("-28.2331452");
			l.setLongitude("-52.3811196");
			entityManager.persist(l);
			solicitado.setLocalizacao(l);
			entityManager.persist(solicitado);
			Historico h = new Historico();
			h.setData(DateTime.now().toDate());
			Localizacao l2 = new Localizacao();
			l2.setLatitude("-28.2669195");
			l2.setLongitude("-52.4073435");	
			entityManager.persist(l2);
			h.setLocalizacao(l2);
			h.setSolicitado(solicitado);
			//entityManager.persist(h);
			hist.add(h);
		}
		
		s.setHistorico(hist);
		
		entityManager.merge(s);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
}