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
public class Qcm{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(cascade=CascadeType.ALL)
	private List<QuestionReponse> listeQuestionsReponses;

	private double noteFinale;
	
	

	public Qcm() {
		this.listeQuestionsReponses = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
