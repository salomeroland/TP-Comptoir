package comptoirs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import comptoirs.dao.CategorieRepository;
import comptoirs.entity.Categorie;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DataJpaTest
class CategorieRepositoryTest {

	Logger logger = LoggerFactory.getLogger(CategorieRepositoryTest.class);

	// Pour générer du JSON
	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private CategorieRepository dao;

	@Test
	@Sql("small_data.sql")
	void compterLesEntites() {
		logger.debug("Compter les entités");

		long nombre = dao.count(); // 'count' donne le nombre d'enregistrements

		assertEquals(2, nombre, "Le jeu de test contient 2 catégories");
	}

	@Test
	@Sql("small_data.sql")
	void listerLesEntites() throws JsonProcessingException {
		logger.debug("Lister les entités");

		List<Categorie> liste = dao.findAll(); // Renvoie la liste des entités dans la table

		logger.debug("Liste des entités: " + mapper.writeValueAsString(liste));
	}
	
	@Test
	@Sql("small_data.sql")
	void listerCustomQuery() throws JsonProcessingException {
		logger.debug("Chercher des entités avec une requête Spring");
		
		String substring = "on";

		List<Categorie> liste = dao.findByLibelleContaining(substring);
		logger.info("Entités trouvées : " + mapper.writeValueAsString(liste));
		
		assertEquals(liste.size(), 2, "I y a deux catégories dont le libéllé contient la sous-chaine");
		
		substring = "xx";
		assertTrue(dao.findByLibelleContaining(substring).isEmpty(), "Aucun libellé de catégorie ne contient cette sous-chaine"); 	
	}
	
	@Test
	@Sql("small_data.sql")
	void touverParCle() throws JsonProcessingException {
		logger.debug("Trouver une entité par sa clé");

		int codePresent = 1;
		Optional<Categorie> resultat = dao.findById(codePresent);
		// On s'assure qu'on trouvé le résultat
		assertTrue(resultat.isPresent(), "Cette catégorie existe");
		Categorie c = resultat.get();
		assertEquals("Boissons", c.getLibelle());

		logger.debug("Entité trouvée : " + mapper.writeValueAsString(c));
	}

	@Test
	@Sql("small_data.sql")
	void entiteInconnue() throws JsonProcessingException {
		logger.debug("Chercher une entité inconnue");
		int codeInconnu = 99;

		Optional<Categorie> resultat = dao.findById(codeInconnu);

		assertFalse(resultat.isPresent(), "Cette catégorie n'existe pas");

	}

	@Test
	@Sql("small_data.sql")
	void creerUneEntite() throws JsonProcessingException {
		logger.debug("Créer une entité");
		Categorie nouvelle = new Categorie();
		nouvelle.setLibelle("essai");
		nouvelle.setDescription("essai");
		assertNull(nouvelle.getCode(), "L'entité n'a pas encore de clé");
		dao.save(nouvelle); // 'save' enregistre l'entite dans la base
		Integer nouvellecle = nouvelle.getCode(); // La clé a été auto-générée lors de l'enregistrement
		assertNotNull(nouvellecle, "Une nouvelle clé doit avoir été générée");
		logger.debug("Nouvelle entités " + mapper.writeValueAsString(nouvelle));
	}

	@Test
	@Sql("small_data.sql")
	void modifierEntite() throws JsonProcessingException {
		logger.debug("Modifier une entité");

		int codePresent = 1;
		String ancienLibelle = "Boissons";
		String nouveauLibelle = "Libellé modifié";
		Categorie c = dao.getOne(codePresent); // On peut utiliser getOne si on est sur qu'elle existe
		assertEquals(ancienLibelle, c.getLibelle());
		// On change l'entité
		c.setLibelle(nouveauLibelle);
		// On l'enregistre dans la base
		dao.save(c);
		// On vérifie que l'enregistrement s'est bien passé
		Categorie recherche = dao.findByLibelle(nouveauLibelle);
		assertEquals(codePresent, recherche.getCode(), "La catégorie doit avoir été modifiée");
	}

	@Test
	@Sql("small_data.sql")
	void erreurCreationEntite() {
		logger.debug("Créer une entité avec erreur");
		Categorie nouvelle = new Categorie();
		nouvelle.setLibelle("Boissons");  // Ce libellé existe dans le jeu de test
		nouvelle.setDescription("essai");
		try { // L'enregistreement peut générer des exceptions (ex : violation de contrainte d'intégrité)
			dao.save(nouvelle);
			fail("Les libellés doivent être tous distincts, on doit avoir une exception");
		} catch (DataIntegrityViolationException e) {
			// Si on arrive ici c'est normal, on a eu l'exception attendue
		}

		assertNull(nouvelle.getCode(), "La clé n'a pas été générée, l'entité n'est pas enregistrée");
	}

	@Test
	@Sql("small_data.sql")
	void onNePeutPasDetruireUneCategorieQuiADesProduits() {
		logger.debug("Détruire une catégorie avec des produits");
		Categorie boissons = dao.getOne(1);
		assertEquals("Boissons", boissons.getLibelle());
		// Il y a des produits dans la catégorie 'Boissons'
		assertFalse(boissons.getProduitList().isEmpty());
		// Si on essaie de détruire cette catégorie, on doit avoir une exception
		// de violation de contrainte d'intégrité
		assertThrows(DataIntegrityViolationException.class, () -> {
			dao.delete(boissons);
			dao.flush();
		});
	}

}
