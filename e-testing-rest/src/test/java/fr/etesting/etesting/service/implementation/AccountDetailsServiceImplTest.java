package fr.etesting.etesting.service.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.service.IAccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountDetailsServiceImplTest {
	@Mock
	private IAccountService accountService;
	@InjectMocks
	private AccountDetailsServiceImpl accountDetailsServiceImpl;

	@Test
	public void testLoadUserByUsername() throws Exception {
		String mail = "admin@test.fr";
		Account account = mock(Account.class);
		
		when(accountService.findByMail(eq(mail))).thenReturn(account);
		when(account.getMail()).thenReturn(mail);
		when(account.getPassword()).thenReturn("password");
		when(account.getAuthorities()).thenReturn(Collections.emptyList());
		
		UserDetails user = accountDetailsServiceImpl.loadUserByUsername(mail);
		
		assertThat(user).isNotNull();
		
		verify(accountService).findByMail(eq(mail));
		verify(account).getMail();
		verify(account).getPassword();
		verify(account).getAuthorities();
	}

}
