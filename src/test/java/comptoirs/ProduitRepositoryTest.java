package comptoirs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import comptoirs.dao.ProduitRepository;
import comptoirs.dto.UnitesParProduit;
import comptoirs.entity.Produit;
import lombok.extern.log4j.Log4j2;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
class ProduitRepositoryTest {
	@Autowired 
	private ProduitRepository daoProduit;
	
	@Test
	@Sql("small_data.sql")		
	void calculCorrectDesStatistiques() throws IOException {
		log.info("Calcul des statistiques");
		int categorieAvecProduit = 1;
		int categorieSansProduit = 2;
		List<UnitesParProduit> results  = daoProduit.produitsVendusPour(categorieSansProduit);
		assertEquals(0, results.size(),   
			"La catégorie 2 n'a pas de produit dans le jeu de test");
		results = daoProduit.produitsVendusPour(categorieAvecProduit);

		assertEquals(2, results.size(),   
			"La catégorie 1 a deux produits différents vendus dans le jeu de test");
		assertEquals(35, results.get(1).getUnites(), "On a vendu 35 unités du produit 'Chang' dans le jeu de test");
	}

	@Test
	@Sql("small_data.sql")		
	void jpqlEtsqlDonnentLeMemeResultat() throws IOException {
		String libelleDeCategorie ="Boissons";
		log.info("Comparaison de requêtes JPQL etSQL");
		log.info("Requête avec JPQL");
		List<Produit> resulatJPQL = daoProduit.produitsPourCategorieJPQL(libelleDeCategorie);
		log.info("Requête avec SQL");
		List<Produit> resultatSQL = daoProduit.produitsPourCategorieSQL(libelleDeCategorie);
		assertEquals(resultatSQL, resulatJPQL, "On doit avoir le même résultat");
		log.info("Résultats de la requête : {}", resulatJPQL);

	}
}
