package fr.etesting.etesting.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Qcm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;

	@OneToMany(cascade = CascadeType.ALL)
	private List<QuestionReponse> listeQuestionsReponses;
	private double noteFinale;
	private double totalPts;

	public Qcm() {
		this.listeQuestionsReponses = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<QuestionReponse> getListeQuestionsReponses() {
		return listeQuestionsReponses;
	}

	public void setListeQuestionsReponses(List<QuestionReponse> listeQuestionsReponses) {
		this.listeQuestionsReponses = listeQuestionsReponses;
	}

	public double getNoteFinale() {
		return noteFinale;
	}

	public void setNoteFinale(double noteFinale) {
		this.noteFinale = noteFinale;
	}

	public double getTotalPts() {
		return totalPts;
	}

	public void setTotalPts(double totalPts) {
		this.totalPts = totalPts;
	}

}
