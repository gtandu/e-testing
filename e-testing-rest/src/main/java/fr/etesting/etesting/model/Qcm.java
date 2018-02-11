package fr.etesting.etesting.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Qcm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany
	private List<QuestionReponse> listQuestionsReponses;
	
	private double noteFinale;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<QuestionReponse> getListQuestionsReponses() {
		return listQuestionsReponses;
	}

	public void setListQuestionsReponses(List<QuestionReponse> listQuestionsReponses) {
		this.listQuestionsReponses = listQuestionsReponses;
	}

	public double getNoteFinale() {
		return noteFinale;
	}

	public void setNoteFinale(double noteFinale) {
		this.noteFinale = noteFinale;
	}
	
	

}
