package fr.etesting.etesting.service;

import java.util.List;

import fr.etesting.etesting.model.Account;

public interface IAccountService {
	
	public Account saveAccount(Account account);
	
	public Account findByMail(String mail);

	public List<Account> findAllAdmin();

}
