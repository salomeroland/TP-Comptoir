package comptoirs.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
	// On fait une contrainte d'unicité sur les 2 clés étrangères
	// plutôt que de faire une clé composite
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {"COMMANDE_NUMERO", "PRODUIT_REFERENCE"})
	}
)
@XmlRootElement
// Lombok
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
public class Ligne implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer id;

	@ManyToOne(optional = false)
	@NonNull
	private Commande commande;

	@ManyToOne(optional = false)
	@NonNull
	private Produit produit;

	@Basic(optional = false)
	@NonNull // lombok
	@NotNull // Java validation
	@Column(nullable = false)
	private Integer quantite;

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Ligne)) {
			return false;
		}
		Ligne other = (Ligne) object;
		return (this.id == null && other.id == null) || (this.id != null && this.id.equals(other.id));
	}
}
