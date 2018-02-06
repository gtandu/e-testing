package fr.etesting.etesting.service;

import fr.etesting.etesting.model.Utilisateur;

public interface IUtilisateurService {
	
	public Utilisateur saveUtilisateur(Utilisateur utilisateur);
	
	public Utilisateur findByMail(String mail);

}
