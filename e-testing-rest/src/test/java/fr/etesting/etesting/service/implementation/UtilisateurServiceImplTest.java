package fr.etesting.etesting.service.implementation;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.etesting.etesting.model.Utilisateur;
import fr.etesting.etesting.repository.UtilisateurRepository;

@RunWith(MockitoJUnitRunner.class)
public class UtilisateurServiceImplTest {
	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@Mock
	private UtilisateurRepository utilisateurRepository;
	@InjectMocks
	private UtilisateurServiceImpl utilisateurServiceImpl;
	

	@Test
	public void testSaveUtilisateur() throws Exception {
		
		Utilisateur utilisateur = new Utilisateur("admin@test.fr","admin", "Joe", "Biceps");
		when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);
		
		utilisateurServiceImpl.saveUtilisateur(utilisateur);
		
		verify(utilisateurRepository).save(any(Utilisateur.class));
	}

	@Test
	public void testFindByMail() throws Exception {
		String mail = "admin@test.fr";
		Utilisateur utilisateur = new Utilisateur(mail,"admin", "Joe", "Biceps");
		
		when(utilisateurRepository.findByMail(eq(mail))).thenReturn(Optional.of(utilisateur));
		
		utilisateurServiceImpl.findByMail(mail);
		
		verify(utilisateurRepository).findByMail(eq(mail));
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void testFindByMailExpectedUsernameNotFoundException() throws Exception {
		String mail = "admin@test.fr";
		
		when(utilisateurRepository.findByMail(eq(mail))).thenReturn(Optional.empty());
		
		utilisateurServiceImpl.findByMail(mail);
		
		verify(utilisateurRepository).findByMail(eq(mail));
	}

}
