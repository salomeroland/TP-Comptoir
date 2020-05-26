package comptoirs.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"COMMANDE", "PRODUIT"})})
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Ligne.findAll", query = "SELECT l FROM Ligne l"),
	@NamedQuery(name = "Ligne.findById", query = "SELECT l FROM Ligne l WHERE l.id = :id"),
	@NamedQuery(name = "Ligne.findByQuantite", query = "SELECT l FROM Ligne l WHERE l.quantite = :quantite")})
public class Ligne implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false)
	private short quantite;

	@JoinColumn(name = "COMMANDE", referencedColumnName = "NUMERO", nullable = false)
	@ManyToOne(optional = false)
	private Commande commande;

	@JoinColumn(name = "PRODUIT", referencedColumnName = "REFERENCE", nullable = false)
	@ManyToOne(optional = false)
	private Produit produit;

	public Ligne() {
	}

	public Ligne(Commande c, Produit p, short quantite) {
		this.commande = c;
		this.produit = p;
		this.quantite = quantite;
	}
	
	public Integer getId() {
		return id;
	}

	public short getQuantite() {
		return quantite;
	}

	public void setQuantite(short quantite) {
		this.quantite = quantite;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Ligne)) {
			return false;
		}
		Ligne other = (Ligne) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "comptoirs.entity.Ligne[ id=" + id + " ]";
	}

}
