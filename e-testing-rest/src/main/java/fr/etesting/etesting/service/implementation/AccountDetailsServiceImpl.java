package fr.etesting.etesting.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.service.IAccountService;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IAccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		 Account account = accountService.findByMail(mail);
		 return new User(account.getMail(), account.getPassword(), account.getAuthorities());
	}

}
