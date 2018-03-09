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
	private double ptsObtenues;
	private double totalPts;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Reponse> listeReponses;

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
	
	public double getPtsObtenues() {
		return ptsObtenues;
	}

	public void setPtsObtenues(double ptsObtenues) {
		this.ptsObtenues = ptsObtenues;
	}

	public double getTotalPts() {
		return totalPts;
	}

	public void setTotalPts(double totalPts) {
		this.totalPts = totalPts;
	}

	public List<Reponse> getListeReponses() {
		return listeReponses;
	}

	public void setListeReponses(List<Reponse> listeReponses) {
		this.listeReponses = listeReponses;
	}

}
