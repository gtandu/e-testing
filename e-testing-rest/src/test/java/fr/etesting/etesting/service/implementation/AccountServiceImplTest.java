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

import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {
	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@Mock
	private AccountRepository utilisateurRepository;
	@InjectMocks
	private AccountServiceImpl utilisateurServiceImpl;
	

	@Test
	public void testSaveUtilisateur() throws Exception {
		
		Account utilisateur = new Account("admin@test.fr","admin", "Joe", "Biceps");
		when(utilisateurRepository.save(any(Account.class))).thenReturn(utilisateur);
		
		utilisateurServiceImpl.saveAccount(utilisateur);
		
		verify(utilisateurRepository).save(any(Account.class));
	}

	@Test
	public void testFindByMail() throws Exception {
		String mail = "admin@test.fr";
		Account utilisateur = new Account(mail,"admin", "Joe", "Biceps");
		
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
