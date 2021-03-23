package comptoirs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Embeddable
// Lombok
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
@XmlRootElement // Pour générer du XML
public class AdressePostale implements Serializable {
	private static final long serialVersionUID = -1147537034606288423L;

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

}
