/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptoirs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rbastide
 */
@Entity
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c"),
	@NamedQuery(name = "Commande.findByNumero", query = "SELECT c FROM Commande c WHERE c.numero = :numero"),
	@NamedQuery(name = "Commande.findBySaisiele", query = "SELECT c FROM Commande c WHERE c.saisiele = :saisiele"),
	@NamedQuery(name = "Commande.findByEnvoyeele", query = "SELECT c FROM Commande c WHERE c.envoyeele = :envoyeele"),
	@NamedQuery(name = "Commande.findByPort", query = "SELECT c FROM Commande c WHERE c.port = :port"),
	@NamedQuery(name = "Commande.findByDestinataire", query = "SELECT c FROM Commande c WHERE c.destinataire = :destinataire"),
	@NamedQuery(name = "Commande.findByAdresseLivraison", query = "SELECT c FROM Commande c WHERE c.adresseLivraison = :adresseLivraison"),
	@NamedQuery(name = "Commande.findByVilleLivraison", query = "SELECT c FROM Commande c WHERE c.villeLivraison = :villeLivraison"),
	@NamedQuery(name = "Commande.findByRegionLivraison", query = "SELECT c FROM Commande c WHERE c.regionLivraison = :regionLivraison"),
	@NamedQuery(name = "Commande.findByCodePostalLivraison", query = "SELECT c FROM Commande c WHERE c.codePostalLivraison = :codePostalLivraison"),
	@NamedQuery(name = "Commande.findByPaysLivraison", query = "SELECT c FROM Commande c WHERE c.paysLivraison = :paysLivraison"),
	@NamedQuery(name = "Commande.findByRemise", query = "SELECT c FROM Commande c WHERE c.remise = :remise")})
public class Commande implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(nullable = false)
	private Integer numero;
	@Basic(optional = false)
        @NotNull
        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
	private Date saisiele;
	@Temporal(TemporalType.DATE)
	private Date envoyeele;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(precision = 18, scale = 2)
	private BigDecimal port;
	@Size(max = 40)
        @Column(length = 40)
	private String destinataire;
	@Size(max = 60)
        @Column(name = "ADRESSE_LIVRAISON", length = 60)
	private String adresseLivraison;
	@Size(max = 15)
        @Column(name = "VILLE_LIVRAISON", length = 15)
	private String villeLivraison;
	@Size(max = 15)
        @Column(name = "REGION_LIVRAISON", length = 15)
	private String regionLivraison;
	@Size(max = 10)
        @Column(name = "CODE_POSTAL_LIVRAISON", length = 10)
	private String codePostalLivraison;
	@Size(max = 15)
        @Column(name = "PAYS_LIVRAISON", length = 15)
	private String paysLivraison;
	@Basic(optional = false)
        @NotNull
        @Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal remise;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
	private List<Ligne> ligneList;
	@JoinColumn(name = "CLIENT", referencedColumnName = "CODE", nullable = false)
        @ManyToOne(optional = false)
	private Client client;

	public Commande() {
	}

	public Commande(Integer numero) {
		this.numero = numero;
	}

	public Commande(Integer numero, Date saisiele, BigDecimal remise) {
		this.numero = numero;
		this.saisiele = saisiele;
		this.remise = remise;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getSaisiele() {
		return saisiele;
	}

	public void setSaisiele(Date saisiele) {
		this.saisiele = saisiele;
	}

	public Date getEnvoyeele() {
		return envoyeele;
	}

	public void setEnvoyeele(Date envoyeele) {
		this.envoyeele = envoyeele;
	}

	public BigDecimal getPort() {
		return port;
	}

	public void setPort(BigDecimal port) {
		this.port = port;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public String getVilleLivraison() {
		return villeLivraison;
	}

	public void setVilleLivraison(String villeLivraison) {
		this.villeLivraison = villeLivraison;
	}

	public String getRegionLivraison() {
		return regionLivraison;
	}

	public void setRegionLivraison(String regionLivraison) {
		this.regionLivraison = regionLivraison;
	}

	public String getCodePostalLivraison() {
		return codePostalLivraison;
	}

	public void setCodePostalLivraison(String codePostalLivraison) {
		this.codePostalLivraison = codePostalLivraison;
	}

	public String getPaysLivraison() {
		return paysLivraison;
	}

	public void setPaysLivraison(String paysLivraison) {
		this.paysLivraison = paysLivraison;
	}

	public BigDecimal getRemise() {
		return remise;
	}

	public void setRemise(BigDecimal remise) {
		this.remise = remise;
	}

	@XmlTransient
	public List<Ligne> getLigneList() {
		return ligneList;
	}

	public void setLigneList(List<Ligne> ligneList) {
		this.ligneList = ligneList;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (numero != null ? numero.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Commande)) {
			return false;
		}
		Commande other = (Commande) object;
		if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "comptoirs.entity.Commande[ numero=" + numero + " ]";
	}
	
}
