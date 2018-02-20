package fr.etesting.etesting.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class QuestionReponse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String libelleQuestion;
	private double totalPts;

	@OneToMany(cascade=CascadeType.ALL)
	private List<ReponseCorrecte> listeReponsesCorrectes;
	@OneToMany(cascade=CascadeType.ALL)
	private List<ReponseFausse> listeReponsesFausses;

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

	public List<ReponseCorrecte> getListeReponsesCorrectes() {
		return listeReponsesCorrectes;
	}

	public void setListeReponsesCorrectes(List<ReponseCorrecte> listeReponsesCorrectes) {
		this.listeReponsesCorrectes = listeReponsesCorrectes;
	}

	public List<ReponseFausse> getListeReponsesFausses() {
		return listeReponsesFausses;
	}

	public void setListeReponsesFausses(List<ReponseFausse> listeReponsesFausses) {
		this.listeReponsesFausses = listeReponsesFausses;
	}

}
