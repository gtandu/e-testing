package fr.etesting.etesting.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Utilisateur {

	@Id
	private String mail;
	private String motDePasse;
	private String nom;
	private String prenom;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateur")
	private Collection<Authority> authorities;
	
	@OneToMany
	private List<Qcm> listQcm;

	public Utilisateur() {
		super();
	}

	public Utilisateur(String mail, String motDePasse, String nom, String prenom) {
		super();
		this.mail = mail;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	public List<Qcm> getListQcm() {
		return listQcm;
	}

	public void setListQcm(List<Qcm> listQcm) {
		this.listQcm = listQcm;
	}
	
	

}
