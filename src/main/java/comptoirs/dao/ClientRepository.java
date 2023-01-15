package comptoirs.dao;


import comptoirs.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring into a Bean called ProductCodeRepository
// CRUD refers Create, Read, Update, Delete

public interface ClientRepository extends JpaRepository<Client, String> {
    /**
     * Calcule le nombre d'articles commandés par un client
     * @param clientCode la clé du client
     */
    @Query("SELECT SUM(l.quantite) FROM Ligne l WHERE l.commande.client.code = :clientCode")
    int nombreArticlesCommandesPar(String clientCode);

}
