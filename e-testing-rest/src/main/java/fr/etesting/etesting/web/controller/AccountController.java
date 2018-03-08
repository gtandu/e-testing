package fr.etesting.etesting.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.model.Authority;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.service.IAccountService;
import fr.etesting.etesting.service.IQcmService;

@RestController
public class AccountController {

	@Autowired
	private IQcmService qcmServiceImpl;
	
	@Autowired
	private IAccountService accountServiceImpl;

	public static final String URL_CORRECT_QCM = "/qcm/{idQcm}";
	
	public static final String URL_ROLES_ACCOUNT = "/account/{mail}/roles";

	
	
	@GetMapping(value=URL_ROLES_ACCOUNT)
	public ResponseEntity<ArrayList<String>> getRolesFromUser(@PathVariable(value = "mail") String mail){
		ArrayList list = new ArrayList<>();
		Collection<Authority> authorities = accountServiceImpl.findByMail(mail).getAuthorities();
		for (Authority authority : authorities) {
			list.add(authority.getRole());
		}
		return new ResponseEntity<ArrayList<String>>(list, HttpStatus.OK);
		
	}

}
