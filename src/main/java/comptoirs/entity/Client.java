package comptoirs.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NonNull // lombok
	@NotNull // Java validation
	@Size(min = 1, max = 5)
	@Column(nullable = false, length = 5)
	private String code;

	@Basic(optional = false)
	@NonNull // lombok
	@NotNull // Java validation
	@Size(min = 1, max = 40)
	@Column(nullable = false, unique = true, length = 40)
	private String societe;

	@Size(max = 30)
	@Column(length = 30)
	private String contact;

	@Size(max = 30)
	@Column(length = 30)
	private String fonction;

	@Size(max = 60)
	@Column(length = 60)
	private String adresse;

	@Size(max = 15)
	@Column(length = 15)
	private String ville;

	@Size(max = 15)
	@Column(length = 15)
	private String region;

	@Size(max = 10)
	@Column(length = 10)
	private String codePostal;

	@Size(max = 15)
	@Column(length = 15)
	private String pays;
	@Size(max = 24)
	@Column(length = 24)
	private String telephone;

	// @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
	@Size(max = 24)
	@Column(length = 24)
	private String fax;

	@JsonIgnore // Ne pas inclure dans le format JSON
	@XmlTransient  // Ne pas inclure dans le format XML
	@ToString.Exclude  // Ne pas inclure dans le toString	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private List<Commande> commandes;

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (code != null ? code.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Client)) {
			return false;
		}
		Client other = (Client) object;
		return (this.code == null && other.code == null) || (this.code != null && this.code.equals(other.code));
	}
}
