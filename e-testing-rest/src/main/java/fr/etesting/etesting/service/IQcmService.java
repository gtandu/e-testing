package fr.etesting.etesting.service;

import java.util.List;

import fr.etesting.etesting.exception.QcmNotFoundException;
import fr.etesting.etesting.model.Qcm;

public interface IQcmService {
	
	public Qcm saveQcm(Qcm qcm);
	public Qcm findQcmById(Long idQcm) throws QcmNotFoundException;
	public Qcm correctQcm(Long idQcm, Qcm qcm, String mail);
	public Qcm updatePts(Qcm qcm);
	public void sendQcmResult(String studentFirstName, String studentLastName, String qcmName, double markQcm);
	public List<Qcm> findAllQcm();
	public Qcm addQuestionReponse(Qcm qcm);
	public Qcm deleteQuestionReponse(Qcm qcm, Long idQr);
	public Qcm addReponse(Qcm qcm, Long idQr);
	public Qcm deleteReponse(Qcm qcm, Long idQr, Long idReponse);


}
