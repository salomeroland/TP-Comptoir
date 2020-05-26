package comptoirs;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import comptoirs.entity.Categorie;
import comptoirs.entity.Client;
import comptoirs.entity.Commande;
import comptoirs.entity.Produit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
public class QueriesTest {
	Logger logger = LoggerFactory.getLogger(ProduitRepositoryTest.class);

	@Autowired
	EntityManager em;	

	@Test
	@Sql("small_data.sql")		
	public void testFindProduitQuery() {
		logger.debug("find by query");
		final List<Produit> produits = em.createNamedQuery("Produit.findAll", Produit.class)
			.getResultList();
		assertEquals(2, produits.size(), "Il y a deux produits dans le jeu de test");	
		Produit p1 = produits.get(0);
		Categorie c1 = p1.getCategorie();
		assertEquals("Boissons", c1.getLibelle(), "Tous les produits du jeu de test sont dans la catégories 'Boissons'");	
	}

	@Test
	@Sql("small_data.sql")		
	public void testDeleteClientSansCommande() {
		logger.debug("Detruire un client qui n'a pas de commande");

		final Client alfki = em.createNamedQuery("Client.findByCode", Client.class)
			.setParameter("code", "ALFKI")
			.getSingleResult();
		
		em.remove(alfki);
	
		final List<Client> clients = em.createNamedQuery("Client.findAll", Client.class).getResultList();
		
		assertFalse(clients.contains(alfki));
	}
	
	@Test
	@Sql("small_data.sql")		
	public void testDeleteClientAvecCommande() {
		logger.debug("Detruire un client qui a des commandes");

		final Client bonap = em.createNamedQuery("Client.findByCode", Client.class)
			.setParameter("code", "BONAP")
			.getSingleResult();

		assertFalse(bonap.getCommandeList().isEmpty(), "Bonap a des commandes");

		em.remove(bonap);
		
		final List<Client> clients = em.createNamedQuery("Client.findAll", Client.class).getResultList();		
		assertEquals(1, clients.size(), "Il ne doit rester qu'un client");	
		
		final List<Commande> commandes = em.createNamedQuery("Commande.findAll", Commande.class).getResultList();		
		assertTrue(commandes.isEmpty(), "Toutes les commandes du jeu de test doivent être détruites");	
	}	
}
