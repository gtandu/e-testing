package fr.etesting.etesting.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Account {

	@Id
	private String mail;
	private String password;
	private String lastname;
	private String firstname;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateur")
	private Collection<Authority> authorities;

	@OneToMany
	private List<Qcm> listQcm;

	public Account() {
		super();
	}

	public Account(String mail, String motDePasse, String nom, String prenom) {
		super();
		this.mail = mail;
		this.password = motDePasse;
		this.lastname = nom;
		this.firstname = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
