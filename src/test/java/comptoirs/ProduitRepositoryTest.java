package comptoirs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import com.fasterxml.jackson.databind.ObjectMapper;

import comptoirs.dao.*;
import comptoirs.dto.StatsResult;
import comptoirs.dto.UnitesParProduit;
import java.io.IOException;
import java.util.List;
import javax.persistence.Tuple;

@DataJpaTest
class ProduitRepositoryTest {
	Logger logger = LoggerFactory.getLogger(ProduitRepositoryTest.class);

	// Pour générer du JSON
	private final ObjectMapper mapper = new ObjectMapper();		

	@Autowired 
	private ProduitRepository daoProduit;
	
	@Test
	@Sql("small_data.sql")		
	void calculCorrectDesStatistiques() throws IOException {
		logger.debug("Calcul des statistiques");
		int categorieAvecProduit = 1;
		int categorieSansProduit = 2;
		List<StatsResult> results  = daoProduit.produitsVendusPour(categorieSansProduit);
		assertEquals(0, results.size(),   
			"La catégorie 2 n'a pas de produit dans le jeu de test");
		results = daoProduit.produitsVendusPour(categorieAvecProduit);
		logger.debug("Résultats de la requête: "+ mapper.writeValueAsString(results));
		assertEquals(2, results.size(),   
			"La catégorie 1 a deux produits différents vendus dans le jeu de test");
		assertEquals(35, results.get(1).unites, "On a vendu 35 unités du produit 'Chang' dans le jeu de test");
	}
	
	@Test
	@Sql("small_data.sql")		
	void calculCorrectDesStatistiquesAvecProjection() throws IOException {
		logger.debug("Calcul des statistiques avec projection");
		int categorieAvecProduit = 1;
		int categorieSansProduit = 2;
		List<UnitesParProduit> results  = daoProduit.produitsVendusPourV3(categorieSansProduit);
		assertEquals(0, results.size(),   
			"La catégorie 2 n'a pas de produit dans le jeu de test");
		results = daoProduit.produitsVendusPourV3(categorieAvecProduit);
		logger.debug("Résultats de la requête avec projection: "+ mapper.writeValueAsString(results));
		assertEquals(2, results.size(),   
			"La catégorie 1 a deux produits différents vendus dans le jeu de test");
		assertEquals(35, results.get(1).getUnites(), "On a vendu 35 unités du produit 'Chang' dans le jeu de test");
	}

	@Test
	@Sql("small_data.sql")		
	void calculCorrectDesStatistiquesAvecTuple() throws IOException {
		logger.debug("Calcul des statistiques avec tuple");
		int categorieAvecProduit = 1;
		int categorieSansProduit = 2;
		List<Tuple> results  = daoProduit.produitsVendusPourV4(categorieSansProduit);
		assertEquals(0, results.size(),   
			"La catégorie 2 n'a pas de produit dans le jeu de test");
		results = daoProduit.produitsVendusPourV4(categorieAvecProduit);
		logger.debug("Résultats de la requête avec tuple: "+ mapper.writeValueAsString(results));
		assertEquals(2, results.size(),   
			"La catégorie 1 a deux produits différents vendus dans le jeu de test");
		assertEquals(35l, results.get(1).get("unites"), "On a vendu 35 unités du produit 'Chang' dans le jeu de test");
	}		
}
