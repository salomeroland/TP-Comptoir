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
		// On cherche le client BONAP d'après sa clé

		// On vérifie qu'il a des commandes
	}
	
	@Test
	@Sql("small_data.sql")		
	void supprimerUnClientSupprimeAussiSesCommandes() {
		logger.debug("On supprime un client");	
		// On vérifie qu'au début, on a deux commandes 

		// On cherche le client BONAP d'après sa clé

		// On supprime le client

		// On vérifie qu'il ne reste aucune commande

		// On vérifie qu'il ne reste aucune ligne

	}
}
