package fr.etesting.etesting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.service.IAccountService;

@Component
public class InitData implements ApplicationRunner {

	@Autowired
	private IAccountService accountService;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		Account utilisateur = new Account("admin@test.fr","admin","Joe","Biceps");
		accountService.saveAccount(utilisateur);
	}

}
