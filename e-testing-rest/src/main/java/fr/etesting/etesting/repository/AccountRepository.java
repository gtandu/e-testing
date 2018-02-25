package fr.etesting.etesting.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.etesting.etesting.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	Optional<Account> findByMail(@Param(value = "mail") String mail);

}
