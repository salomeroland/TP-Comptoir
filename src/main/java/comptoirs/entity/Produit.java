package comptoirs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"NOM"})})
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p"),
	@NamedQuery(name = "Produit.findByReference", query = "SELECT p FROM Produit p WHERE p.reference = :reference"),
	@NamedQuery(name = "Produit.findByNom", query = "SELECT p FROM Produit p WHERE p.nom = :nom"),
	@NamedQuery(name = "Produit.findByFournisseur", query = "SELECT p FROM Produit p WHERE p.fournisseur = :fournisseur"),
	@NamedQuery(name = "Produit.findByQuantiteParUnite", query = "SELECT p FROM Produit p WHERE p.quantiteParUnite = :quantiteParUnite"),
	@NamedQuery(name = "Produit.findByPrixUnitaire", query = "SELECT p FROM Produit p WHERE p.prixUnitaire = :prixUnitaire"),
	@NamedQuery(name = "Produit.findByUnitesEnStock", query = "SELECT p FROM Produit p WHERE p.unitesEnStock = :unitesEnStock"),
	@NamedQuery(name = "Produit.findByUnitesCommandees", query = "SELECT p FROM Produit p WHERE p.unitesCommandees = :unitesCommandees"),
	@NamedQuery(name = "Produit.findByNiveauDeReappro", query = "SELECT p FROM Produit p WHERE p.niveauDeReappro = :niveauDeReappro"),
	@NamedQuery(name = "Produit.findByIndisponible", query = "SELECT p FROM Produit p WHERE p.indisponible = :indisponible")})
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer reference;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(nullable = false, length = 40)
	private String nom;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false)
	private int fournisseur;

	@Size(max = 30)
	@Column(name = "QUANTITE_PAR_UNITE", length = 30)
	private String quantiteParUnite;

	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Column(name = "PRIX_UNITAIRE", nullable = false, precision = 18, scale = 2)
	private BigDecimal prixUnitaire;

	@Basic(optional = false)
	@NotNull
	@Column(name = "UNITES_EN_STOCK", nullable = false)
	private short unitesEnStock;

	@Basic(optional = false)
	@NotNull
	@Column(name = "UNITES_COMMANDEES", nullable = false)
	private short unitesCommandees;

	@Basic(optional = false)
	@NotNull
	@Column(name = "NIVEAU_DE_REAPPRO", nullable = false)
	private short niveauDeReappro;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false)
	private short indisponible;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produit")
	private List<Ligne> ligneList;

	@JoinColumn(name = "CATEGORIE", referencedColumnName = "CODE", nullable = false)
	@ManyToOne(optional = false)
	private Categorie categorie;

	public Produit() {
	}

	public Produit(Integer reference) {
		this.reference = reference;
	}

	public Produit(Integer reference, String nom, int fournisseur, BigDecimal prixUnitaire, short unitesEnStock, short unitesCommandees, short niveauDeReappro, short indisponible) {
		this.reference = reference;
		this.nom = nom;
		this.fournisseur = fournisseur;
		this.prixUnitaire = prixUnitaire;
		this.unitesEnStock = unitesEnStock;
		this.unitesCommandees = unitesCommandees;
		this.niveauDeReappro = niveauDeReappro;
		this.indisponible = indisponible;
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(int fournisseur) {
		this.fournisseur = fournisseur;
	}

	public String getQuantiteParUnite() {
		return quantiteParUnite;
	}

	public void setQuantiteParUnite(String quantiteParUnite) {
		this.quantiteParUnite = quantiteParUnite;
	}

	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public short getUnitesEnStock() {
		return unitesEnStock;
	}

	public void setUnitesEnStock(short unitesEnStock) {
		this.unitesEnStock = unitesEnStock;
	}

	public short getUnitesCommandees() {
		return unitesCommandees;
	}

	public void setUnitesCommandees(short unitesCommandees) {
		this.unitesCommandees = unitesCommandees;
	}

	public short getNiveauDeReappro() {
		return niveauDeReappro;
	}

	public void setNiveauDeReappro(short niveauDeReappro) {
		this.niveauDeReappro = niveauDeReappro;
	}

	public short getIndisponible() {
		return indisponible;
	}

	public void setIndisponible(short indisponible) {
		this.indisponible = indisponible;
	}

	@XmlTransient
	public List<Ligne> getLigneList() {
		return ligneList;
	}

	public void setLigneList(List<Ligne> ligneList) {
		this.ligneList = ligneList;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (reference != null ? reference.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Produit)) {
			return false;
		}
		Produit other = (Produit) object;
		if ((this.reference == null && other.reference != null) || (this.reference != null && !this.reference.equals(other.reference))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "comptoirs.entity.Produit[ reference=" + reference + " ]";
	}

}
