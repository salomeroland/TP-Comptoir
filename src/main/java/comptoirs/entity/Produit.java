package comptoirs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
// Lombok
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@XmlRootElement // Pour générer du XML
public class Produit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer reference;

	@Basic(optional = false)
	@NonNull // lombok
	@NotNull // Java validation
	@Size(min = 1, max = 40)
	@Column(nullable = false, unique= true, length = 40)
	private String nom;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false)
	private int fournisseur;

	@Size(max = 30)
	@Column(length = 30)
	private String quantiteParUnite;

	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Column(nullable = false, precision = 18, scale = 2)
	private BigDecimal prixUnitaire;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false)
	private short unitesEnStock;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false)
	private short unitesCommandees;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false)
	private short niveauDeReappro;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false)
	private short indisponible;

	@JsonIgnore // Ne pas inclure dans le format JSON
	@XmlTransient  // Ne pas inclure dans le format XML
	@ToString.Exclude  // Ne pas inclure dans le toString	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produit")
	private List<Ligne> lignes  = new LinkedList<>();

	@ManyToOne(optional = false)
	private Categorie categorie;


	@Override
	public int hashCode() {
		int hash = 0;
		hash += (reference != null ? reference.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Produit)) {
			return false;
		}
		Produit other = (Produit) object;
		return ((this.reference == null && other.reference == null) || (this.reference != null && this.reference.equals(other.reference)));
	}
}
