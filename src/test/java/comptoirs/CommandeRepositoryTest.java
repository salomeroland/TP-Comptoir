package comptoirs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import comptoirs.dao.*;
import comptoirs.entity.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.EntityManager;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
class CommandeRepositoryTest {
	Logger logger = LoggerFactory.getLogger(CommandeRepositoryTest.class);
	
	@Autowired
	EntityManager em;
	
	@Autowired 
	private CommandeRepository daoCommande;

	@Autowired 
	private ClientRepository daoClient;

	@Autowired 
	private ProduitRepository daoProduit;
	
	@Autowired
	private LigneRepository daoLigne;

	@Test
	@Sql("small_data.sql")		
	void onPeutCreerUneCommandeEtSesLignes() {
		logger.debug("Création d'une commande avec ses lignes");
		// On cherche les infos nécessaires dans le jeu d'essai
		Produit chang = daoProduit.getOne(2);
		Produit syrup = daoProduit.getOne(3);
		Client alkfi  = daoClient.getOne("ALFKI");

		// On crée une commande
		Commande nouvelle = new Commande();
		// On définit au moins les propriétés non NULL
		nouvelle.setClient(alkfi);
		nouvelle.setSaisiele(new Date());
		nouvelle.setRemise(BigDecimal.ZERO);
				
		// On crée deux lignes pour la nouvelle commande
		Ligne l1 = new Ligne(nouvelle, syrup, (short)4);
		Ligne l2 = new Ligne(nouvelle, chang, (short)10);
		
		ArrayList<Ligne> lignes = new ArrayList<>();
		lignes.add(l1); lignes.add(l2);

		// On ajoute les deux lignes à la commande
		nouvelle.setLigneList(lignes);

		// On enregistre la commande (provoque l'enregistrement des lignes)
		daoCommande.save(nouvelle);
				
		// On regarde si ça s'est bien passé
		assertEquals(5, daoLigne.findAll().size(),   "Il doit y avoir 5 lignes en tout");
		assertEquals(3, chang.getLigneList().size(), "Il doit y avoir 3 lignes pour le produit 'Chang')");
		assertEquals(2, syrup.getLigneList().size(), "Il doit y avoir 2 lignes pour le produit 'Syrup')");
		assertTrue(chang.getLigneList().contains(l2), "La nouvelle ligne doit avoir été ajoutée au produit 'chang'");
		assertTrue(syrup.getLigneList().contains(l1), "La nouvelle ligne doit avoir été ajoutée au produit 'syrup'");		
	}
	
	@Test
	@Sql("small_data.sql")		
	void pasDeuxFoisLeMemeProduitDansUneCommande() {
		logger.debug("Tentative de création d'une commande avec doublon");	
		// On cherche les infos nécessaires dans le jeu d'essai
		Produit chang = daoProduit.getOne(2);
		Client alkfi  = daoClient.getOne("ALFKI");

		// On crée une commande
		Commande nouvelle = new Commande();
		// On définit au moins les propriétés non NULL
		nouvelle.setClient(alkfi);
		nouvelle.setSaisiele(new Date());
		nouvelle.setRemise(BigDecimal.ZERO);
				
		// On crée deux lignes pour la nouvelle commande avec le même produit
		Ligne l1 = new Ligne(nouvelle, chang, (short)4);
		Ligne l2 = new Ligne(nouvelle, chang, (short)10);
		
		ArrayList<Ligne> lignes = new ArrayList<>();
		lignes.add(l1); lignes.add(l2);

		// On ajoute les deux lignes à la commande
		nouvelle.setLigneList(lignes);

		try { // La création de la commande doit produire une erreur
			daoCommande.save(nouvelle);
			fail("La commande ne doit pas être sauvegardée");
		} catch (DataIntegrityViolationException e) {
			logger.debug("La création a échoué : {}", e.getMessage());
		}
	}
}
