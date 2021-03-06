package fr.etesting.etesting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reponse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String libelleReponse;
	private double points;
	private boolean bonneReponse;
	private boolean repondu;
	
	
	
	public Reponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reponse(String libelleReponse) {
		super();
		this.libelleReponse = libelleReponse;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLibelleReponse() {
		return libelleReponse;
	}
	public void setLibelleReponse(String libelleReponse) {
		this.libelleReponse = libelleReponse;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public boolean isBonneReponse() {
		return bonneReponse;
	}
	public void setBonneReponse(boolean bonneReponse) {
		this.bonneReponse = bonneReponse;
	}
	public boolean isRepondu() {
		return repondu;
	}
	public void setRepondu(boolean repondu) {
		this.repondu = repondu;
	}
	
	

}
