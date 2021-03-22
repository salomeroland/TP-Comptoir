package comptoirs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import comptoirs.dao.ProduitRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import comptoirs.entity.Categorie;
import comptoirs.entity.Produit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
public class EntityManagerTest {
	Logger logger = LoggerFactory.getLogger(EntityManagerTest.class);
	
	// Pour produire du JSON
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired 
	ProduitRepository dao;
	
	@Autowired
	EntityManager em;	

	@Test
	@Sql("small_data.sql")		
	public void testCreationCategorieAvecProduits() {
		logger.debug("Création catégorie avec produits");
		long produitsAvant = dao.count();
		Categorie cat = new Categorie();
		cat.setDescription("logiciel");
		cat.setLibelle("Logiciels et utilitaires");
		Produit p1 = new Produit();
		p1.setNom("Windows 10"); p1.setPrixUnitaire(BigDecimal.ZERO);
		p1.setCategorie(cat);
		Produit p2 = new Produit();
		p2.setNom("Norton Antivirus"); p2.setPrixUnitaire(BigDecimal.TEN);
		p2.setCategorie(cat);
		List<Produit> produits = new ArrayList<>();
		produits.add(p1);
		produits.add(p2);
		cat.setProduits(produits);
		logger.info("On enregistre la catégorie et ses 2 produits");
		em.persist(cat);
		long produitsAprès = dao.count();
		assertEquals(produitsAvant + 2, produitsAprès, 
			"L'ajout de la catégorie doit provoquer l'ajout de ses 2 produits" );

	}

	@Test
	@Sql("small_data.sql")		
	public void testRechercheCategorieAvecProduits() throws JsonProcessingException {
		logger.debug("Recherche catégorie avec produits");
		// Recherche par clé
		Categorie cat = em.find(Categorie.class, 1);
		List<Produit> produits = cat.getProduits();
		// On a chargé également tous les produits de cette catégorie
		assertEquals(2, produits.size(), "La catégorie 1 a 2 produits dans le jeu de test");
	}
}
