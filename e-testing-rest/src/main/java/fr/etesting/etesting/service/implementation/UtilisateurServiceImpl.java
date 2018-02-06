package fr.etesting.etesting.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.etesting.etesting.model.Utilisateur;
import fr.etesting.etesting.repository.UtilisateurRepository;
import fr.etesting.etesting.service.IUtilisateurService;

@Service
public class UtilisateurServiceImpl implements IUtilisateurService {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur findByMail(String mail) {
		Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByMail(mail);
		if(optionalUtilisateur.isPresent()){
			return optionalUtilisateur.get();
		}
		else
		{
			throw new UsernameNotFoundException(mail);
		}
	}
	
	

}
