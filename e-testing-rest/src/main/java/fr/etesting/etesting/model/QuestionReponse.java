package fr.etesting.etesting.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class QuestionReponse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String libelleQuestion;
	private double totalPts;
	
	@OneToMany
	private List<Reponse> listeReponseVraie;
	@OneToMany
	private List<Reponse> listeReponseFausse;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelleQuestion() {
		return libelleQuestion;
	}
	public void setLibelleQuestion(String libelleQuestion) {
		this.libelleQuestion = libelleQuestion;
	}
	public double getTotalPts() {
		return totalPts;
	}
	public void setTotalPts(double totalPts) {
		this.totalPts = totalPts;
	}
	public List<Reponse> getListeReponseVraie() {
		return listeReponseVraie;
	}
	public void setListeReponseVraie(List<Reponse> listeReponseVraie) {
		this.listeReponseVraie = listeReponseVraie;
	}
	public List<Reponse> getListeReponseFausse() {
		return listeReponseFausse;
	}
	public void setListeReponseFausse(List<Reponse> listeReponseFausse) {
		this.listeReponseFausse = listeReponseFausse;
	}
	
}
