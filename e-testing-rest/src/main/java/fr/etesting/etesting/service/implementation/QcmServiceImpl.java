package fr.etesting.etesting.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	

}
