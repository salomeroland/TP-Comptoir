package comptoirs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import comptoirs.entity.Categorie;
import comptoirs.entity.Produit;
import lombok.extern.log4j.Log4j2;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class EntityManagerTest {
	
	@Autowired 
	ProduitRepository dao;
	
	@Autowired
	EntityManager em;	

	@Test
	@Sql("small_data.sql")		
	void testCreationCategorieAvecProduits() {
		log.info("Création catégorie avec produits");
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
		log.info("On enregistre la catégorie et ses 2 produits");
		em.persist(cat);
		long produitsAprès = dao.count();
		assertEquals(produitsAvant + 2, produitsAprès, 
			"L'ajout de la catégorie doit provoquer l'ajout de ses 2 produits" );

	}

	@Test
	@Sql("small_data.sql")		
	void testRechercheCategorieAvecProduits() {
		log.info("Recherche catégorie avec produits");
		// Recherche par clé
		Categorie cat = em.find(Categorie.class, 98);
		var produits = cat.getProduits();
		// On a chargé également tous les produits de cette catégorie
		assertEquals(3, produits.size(), "La catégorie a 3 produits dans le jeu de test");
	}
}
