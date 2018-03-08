package fr.etesting.etesting.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.model.Authority;
import fr.etesting.etesting.model.enumeration.RoleAccountEnum;
import fr.etesting.etesting.service.IAccountService;

@Component
public class InitData implements ApplicationRunner {

	@Autowired
	private IAccountService accountService;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		Account gtandu = new Account("g.tandu@hotmail.fr","edu","TANDU","Glodie");
		Account cmapella = new Account("mapella.corentin@gmail.com","edu","MAPELLA","Corentin");
		Account dcourtaud = new Account("didier.courtaud@univ-evry.fr","ens","COURTAUD","Didier");
		Authority authorityAdmin = new Authority();
		authorityAdmin.setRole(RoleAccountEnum.Administrator.getRole());
		dcourtaud.setAuthorities(Arrays.asList(authorityAdmin));
		accountService.saveAccount(gtandu);
		accountService.saveAccount(cmapella);
		accountService.saveAccount(dcourtaud);

	}

}
