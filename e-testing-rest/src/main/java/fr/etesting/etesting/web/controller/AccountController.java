package fr.etesting.etesting.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.service.IQcmService;

@RestController
public class AccountController {

	@Autowired
	private IQcmService qcmServiceImpl;

	public static final String URL_CORRECT_QCM = "/qcm/{idQcm}";

	@PostMapping(value = URL_CORRECT_QCM)
	public ResponseEntity<Account> correctQcm(@PathVariable(value = "idQcm") Long idQcm, Principal principal,
			@RequestBody Qcm qcmToCorrect) {

		Account account = qcmServiceImpl.correctQcm(idQcm, qcmToCorrect, principal.getName());
		return new ResponseEntity<>(account, HttpStatus.OK);

	}

}
