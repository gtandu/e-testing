package fr.etesting.etesting.service;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.etesting.etesting.model.Utilisateur;

@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUtilisateurService utilisateurService;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		 Utilisateur utilisateur = utilisateurService.findByMail(mail);
			return new User(utilisateur.getMail(), utilisateur.getMotDePasse(), emptyList());
	}

}
