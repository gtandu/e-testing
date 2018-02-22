package fr.etesting.etesting.service;

import fr.etesting.etesting.exception.QcmNotFoundException;
import fr.etesting.etesting.model.Qcm;

public interface IQcmService {
	
	public Qcm saveQcm(Qcm qcm);
	public Qcm findQcmById(Long idQcm) throws QcmNotFoundException;

}
