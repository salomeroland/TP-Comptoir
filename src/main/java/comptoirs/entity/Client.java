/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptoirs.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rbastide
 */
@Entity
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"SOCIETE"})})
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
	@NamedQuery(name = "Client.findByCode", query = "SELECT c FROM Client c WHERE c.code = :code"),
	@NamedQuery(name = "Client.findBySociete", query = "SELECT c FROM Client c WHERE c.societe = :societe"),
	@NamedQuery(name = "Client.findByContact", query = "SELECT c FROM Client c WHERE c.contact = :contact"),
	@NamedQuery(name = "Client.findByFonction", query = "SELECT c FROM Client c WHERE c.fonction = :fonction"),
	@NamedQuery(name = "Client.findByAdresse", query = "SELECT c FROM Client c WHERE c.adresse = :adresse"),
	@NamedQuery(name = "Client.findByVille", query = "SELECT c FROM Client c WHERE c.ville = :ville"),
	@NamedQuery(name = "Client.findByRegion", query = "SELECT c FROM Client c WHERE c.region = :region"),
	@NamedQuery(name = "Client.findByCodePostal", query = "SELECT c FROM Client c WHERE c.codePostal = :codePostal"),
	@NamedQuery(name = "Client.findByPays", query = "SELECT c FROM Client c WHERE c.pays = :pays"),
	@NamedQuery(name = "Client.findByTelephone", query = "SELECT c FROM Client c WHERE c.telephone = :telephone"),
	@NamedQuery(name = "Client.findByFax", query = "SELECT c FROM Client c WHERE c.fax = :fax")})
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 5)
        @Column(nullable = false, length = 5)
	private String code;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 40)
        @Column(nullable = false, length = 40)
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
        @Column(name = "CODE_POSTAL", length = 10)
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
	private List<Commande> commandeList;

	public Client() {
	}

	public Client(String code) {
		this.code = code;
	}

	public Client(String code, String societe) {
		this.code = code;
		this.societe = societe;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@XmlTransient
	public List<Commande> getCommandeList() {
		return commandeList;
	}

	public void setCommandeList(List<Commande> commandeList) {
		this.commandeList = commandeList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (code != null ? code.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Client)) {
			return false;
		}
		Client other = (Client) object;
		if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "comptoirs.entity.Client[ code=" + code + " ]";
	}
	
}
