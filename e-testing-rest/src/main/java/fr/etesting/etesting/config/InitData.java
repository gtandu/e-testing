package fr.etesting.etesting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.etesting.etesting.model.Utilisateur;
import fr.etesting.etesting.service.IUtilisateurService;

@Component
public class InitData implements ApplicationRunner {

	@Autowired
	private IUtilisateurService utilisateurService;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		Utilisateur utilisateur = new Utilisateur("admin@test.fr","admin","Joe","Biceps");
		utilisateurService.saveUtilisateur(utilisateur);
	}

}
