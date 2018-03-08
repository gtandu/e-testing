package fr.etesting.etesting.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.etesting.etesting.exception.QcmNotFoundException;
import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.model.QuestionReponse;
import fr.etesting.etesting.model.Reponse;
import fr.etesting.etesting.repository.QcmRepository;
import fr.etesting.etesting.service.IAccountService;
import fr.etesting.etesting.service.IQcmService;

@Service
public class QcmServiceImpl implements IQcmService {

	@Autowired
	private QcmRepository qcmRepository;
	
	@Autowired
	private IAccountService accountService;

	private static final double noteSur20 = 20.0;

	@Override
	public Qcm saveQcm(Qcm qcm) {
		return qcmRepository.save(qcm);
	}

	@Override
	public Qcm findQcmById(Long idQcm) throws QcmNotFoundException {
		Optional<Qcm> qcmOptional = qcmRepository.findById(idQcm);
		if (qcmOptional.isPresent()) {
			return qcmOptional.get();
		} else {
			throw new QcmNotFoundException();
		}
	}

	@Override
	public Account correctQcm(Long idQcm, Qcm qcm, String mail) {
		Account account = accountService.findByMail(mail);
		double noteFinale = 0;
		List<QuestionReponse> listeQuestionsReponses = qcm.getListeQuestionsReponses();
		for (QuestionReponse questionReponse : listeQuestionsReponses) {
			List<Reponse> listeReponses = questionReponse.getListeReponses();
			for (Reponse reponse : listeReponses) {
				if (reponse.isRepondu()) {
					noteFinale += reponse.getPoints();
				}
			}
		}

		double diviseur = qcm.getTotalPts() / noteSur20;
		double note = noteFinale /= diviseur;
		qcm.setNoteFinale(note);
		System.out.println(note);
		account.getListQcmNotes().put(qcm, note);
		return accountService.saveAccount(account);
	}

	@Override
	public Qcm updatePts(Qcm qcm) {
		for (QuestionReponse questionReponse : qcm.getListeQuestionsReponses()) {
			questionReponse.getTotalPts();
			for (Reponse reponse : questionReponse.getListeReponses()) {
				if(reponse.getPoints() > 0) {					
					questionReponse.setTotalPts(questionReponse.getTotalPts() + reponse.getPoints());
				}
			}
		}
		return qcm;
	}

}
