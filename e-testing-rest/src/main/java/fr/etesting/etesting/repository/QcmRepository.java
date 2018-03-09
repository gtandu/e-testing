package fr.etesting.etesting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.etesting.etesting.model.Qcm;

public interface QcmRepository extends CrudRepository<Qcm, Long> {
	
	public Optional<Qcm> findById(Long id);
	public List<Qcm> findAll();

}
