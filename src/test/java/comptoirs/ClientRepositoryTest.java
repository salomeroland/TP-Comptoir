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
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
class ClientRepositoryTest {
	Logger logger = LoggerFactory.getLogger(ClientRepositoryTest.class);
	
	@Autowired 
	private CommandeRepository daoCommande;

	@Autowired 
	private ClientRepository daoClient;
	
	@Autowired
	private LigneRepository daoLigne;

	@Test
	@Sql("small_data.sql")		
	void onPeutTrouverUnClientEtSesCommandes() {
		logger.debug("Recherche d'un client");
		// On cherche le client d'après sa clé
		Client bonap  = daoClient.getOne("BONAP");
		// On vérifie qu'il a des commandes
		assertEquals(2, bonap.getCommandeList().size(), "Bonap à deux commandes dans le jeu de tests");
	}
	
	@Test
	@Sql("small_data.sql")		
	void supprimerUnClientSupprimeAussiSesCommandes() {
		logger.debug("On supprime un client");	
		// Au début, on a deux commandes 
		assertEquals(2, daoCommande.count(), "Il y a deux commandes dans le jeu de tests");
		// On cherche le client d'après sa clé
		Client bonap  = daoClient.getOne("BONAP");
		// On supprime le client
		daoClient.delete(bonap);
		// On vérifie qu'il ne reste aucune commande
		assertEquals(0, daoCommande.count(), "Il ne reste aucune commande après la suppression du client");
		// On vérifie qu'il ne reste aucune ligne
		assertEquals(0, daoLigne.count(), "Il ne reste aucune ligne de commande après la suppression du client");
	}
}
