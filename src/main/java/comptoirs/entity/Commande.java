package comptoirs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import lombok.Setter;
import lombok.ToString;

@Entity
@XmlRootElement
// Lombok
@Getter @Setter @NoArgsConstructor @ToString
public class Commande implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer numero;

	@Basic(optional = false)
	@Column(nullable = false)
	private LocalDate saisiele  = LocalDate.now();

	private LocalDate envoyeele;

	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(precision = 18, scale = 2)
	private BigDecimal port = BigDecimal.ZERO;

	@Size(max = 40)
	@Column(length = 40)
	private String destinataire;

	@Embedded AdressePostale adresseLivraison;

	@Basic(optional = false)
	@NotNull
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal remise = BigDecimal.ZERO;

	@JsonIgnore // Ne pas inclure dans le format JSON
	@XmlTransient  // Ne pas inclure dans le format XML
	@ToString.Exclude  // Ne pas inclure dans le toString	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commande", orphanRemoval = true)
	private List<Ligne> lignes;

	@ManyToOne(optional = false)
	private Client client;

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (numero != null ? numero.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Commande)) {
			return false;
		}
		Commande other = (Commande) object;
		return (this.numero == null && other.numero == null) || (this.numero != null && this.numero.equals(other.numero));
	}
}
