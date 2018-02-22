package fr.etesting.etesting.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.etesting.etesting.exception.QcmNotFoundException;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.repository.QcmRepository;
import fr.etesting.etesting.service.IQcmService;

@Service
public class QcmServiceImpl implements IQcmService{
	
	@Autowired
	private QcmRepository qcmRepository;

	@Override
	public Qcm saveQcm(Qcm qcm) {
		return qcmRepository.save(qcm);
	}

	@Override
	public Qcm findQcmById(Long idQcm) throws QcmNotFoundException {
		Optional<Qcm> qcmOptional = qcmRepository.findById(idQcm);
		if(qcmOptional.isPresent()) {
			return qcmOptional.get();
		}
		else
		{
			throw new QcmNotFoundException();
		}
	}
	
	

}
