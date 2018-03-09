package fr.etesting.etesting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.etesting.etesting.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	Optional<Account> findByMail(@Param(value = "mail") String mail);
	
	@Query("SELECT a from Account a WHERE a.authorities IS NOT EMPTY")
	List<Account> findAllAdmin();

}
