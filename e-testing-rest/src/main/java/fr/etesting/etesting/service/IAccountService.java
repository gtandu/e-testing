package fr.etesting.etesting.service;

import fr.etesting.etesting.model.Account;

public interface IAccountService {
	
	public Account saveAccount(Account account);
	
	public Account findByMail(String mail);

}
