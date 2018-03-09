package fr.etesting.etesting.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.repository.AccountRepository;
import fr.etesting.etesting.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Account saveAccount(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		return accountRepository.save(account);
	}

	@Override
	public Account findByMail(String mail) {
		Optional<Account> optionalAccount = accountRepository.findByMail(mail);
		if(optionalAccount.isPresent()){
			return optionalAccount.get();
		}
		else
		{
			throw new UsernameNotFoundException(mail);
		}
	}

	@Override
	public List<Account> findAllAdmin() {
		return  accountRepository.findAllAdmin();
	}
	
	

}
