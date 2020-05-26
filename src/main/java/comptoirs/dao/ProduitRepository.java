package comptoirs.dao;

import java.util.List;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comptoirs.entity.Produit;
import comptoirs.dto.StatsResult;
import comptoirs.dto.UnitesParProduit;

// Cette interface sera auto-implémentée par Spring

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
	/**
	 * Calcule le nombre d'unités vendues pour chaque produit d'une catégorie donnée.
	 * Utilise le DTO 'StatsResult' pour représenter les résultats de la requête
	 * @param codeCategorie la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit, 
	 *		sous la forme d'une liste de StatsResult(nom du produit, quantité vendue)
	 */
	@Query("SELECT new comptoirs.dto.StatsResult( p.nom, SUM(li.quantite) ) "
		+ "FROM Categorie c "
		+ "JOIN c.produitList p "
		+ "JOIN p.ligneList li "
		+ "WHERE c.code = :codeCategorie "
		+ "GROUP BY p.nom ")
	public List<StatsResult> produitsVendusPour(Integer codeCategorie);
	
	/**
	 * Calcule le nombre d'unités vendues pour chaque produit d'une catégorie donnée.
	 * pas d'utilisation de DTO
	 * @param codeCategorie la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit, 
	 *		sous la forme d'une liste de valeurs non typées
	 */	
	@Query("SELECT p.nom, SUM(li.quantite) "
		+ "FROM Categorie c "
		+ "JOIN c.produitList p "
		+ "JOIN p.ligneList li "
		+ "WHERE c.code = :codeCategorie "
		+ "GROUP BY p.nom ")
	public List produitsVendusPourV2(Integer codeCategorie);

	/**
	 * Calcule le nombre d'unités vendues pour chaque produit d'une catégorie donnée.
	 * pas d'utilisation de DTO
	 * @param codeCategorie la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit, 
	 *		sous la forme d'une liste de valeurs non typées
	 */	
	@Query("SELECT p.nom as nom, SUM(li.quantite) AS unites "
		+ "FROM Categorie c "
		+ "JOIN c.produitList p "
		+ "JOIN p.ligneList li "
		+ "WHERE c.code = :codeCategorie "
		+ "GROUP BY p.nom ")
	public List<UnitesParProduit> produitsVendusPourV3(Integer codeCategorie);

	/**
	 * Calcule le nombre d'unités vendues pour chaque produit d'une catégorie donnée.
	 * pas d'utilisation de DTO
	 * @param codeCategorie la catégorie à traiter
	 * @return le nombre d'unités vendus pour chaque produit, 
	 *		sous la forme d'une liste de valeurs non typées
	 */	
	@Query("SELECT p.nom as nom, SUM(li.quantite) AS unites "
		+ "FROM Categorie c "
		+ "JOIN c.produitList p "
		+ "JOIN p.ligneList li "
		+ "WHERE c.code = :codeCategorie "
		+ "GROUP BY p.nom ")
	public List<Tuple> produitsVendusPourV4(Integer codeCategorie);		
}
