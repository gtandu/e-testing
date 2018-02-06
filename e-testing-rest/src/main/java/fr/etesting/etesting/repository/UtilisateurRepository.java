package fr.etesting.etesting.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.etesting.etesting.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
	
	Optional<Utilisateur> findByMail(@Param(value = "mail") String mail);

}
