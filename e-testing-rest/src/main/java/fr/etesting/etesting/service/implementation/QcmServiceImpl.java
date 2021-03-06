package fr.etesting.etesting.service.implementation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
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
import fr.etesting.etesting.service.mail.PublishMarkMail;

@Service
public class QcmServiceImpl implements IQcmService {

	@Autowired
	private QcmRepository qcmRepository;

	@Autowired
	private PublishMarkMail publishMarkMail;

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
	public Qcm correctQcm(Long idQcm, Qcm qcm, String mail) {
		Account account = accountService.findByMail(mail);
		double noteFinale = 0;
		List<QuestionReponse> listeQuestionsReponses = qcm.getListeQuestionsReponses();
		for (QuestionReponse questionReponse : listeQuestionsReponses) {
			List<Reponse> listeReponses = questionReponse.getListeReponses();
			for (Reponse reponse : listeReponses) {
				if (reponse.isRepondu()) {
					noteFinale += reponse.getPoints();
					questionReponse.setPtsObtenues(questionReponse.getPtsObtenues() + reponse.getPoints());
				}
			}
		}

		double diviseur = qcm.getTotalPts() / noteSur20;
		Double note = noteFinale /= diviseur;
		qcm.setNoteFinale(QcmServiceImpl.round(note, 2));
		this.sendQcmResult(account.getFirstname(), account.getLastname(), qcm.getNom(), qcm.getNoteFinale());
		return qcm;
	}

	@Override
	public Qcm updatePts(Qcm qcm) {
		for (QuestionReponse questionReponse : qcm.getListeQuestionsReponses()) {
			questionReponse.setTotalPts(0);
			for (Reponse reponse : questionReponse.getListeReponses()) {
					if (reponse.getPoints() > 0) {
						questionReponse.setTotalPts(questionReponse.getTotalPts() + reponse.getPoints());
					}
			}
			qcm.setTotalPts(qcm.getTotalPts() + questionReponse.getTotalPts());
		}
		return qcm;
	}

	@Override
	public void sendQcmResult(String studentFirstName, String studentLastName, String qcmName, double markQcm) {
		List<Account> findAllAdmin = accountService.findAllAdmin();
		for (Account account : findAllAdmin) {
			publishMarkMail.sendMailPublishMarks(studentFirstName, studentLastName, account.getMail(), qcmName,
					markQcm);
		}
	}

	@Override
	public List<Qcm> findAllQcm() {
		return qcmRepository.findAll();
	}

	private static double round(double value, int places) {
		if (places < 0)
		{
			throw new IllegalArgumentException();			
		}

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	@Override
	public Qcm addQuestionReponse(Qcm qcm) {
		QuestionReponse questionReponse = new QuestionReponse("A saisir");
		qcm.getListeQuestionsReponses().add(questionReponse);

		return this.saveQcm(qcm);
	}

	@Override
	public Qcm deleteQuestionReponse(Qcm qcm, Long idQr) {
		qcm.getListeQuestionsReponses().iterator();
		for (Iterator<QuestionReponse> iterator = qcm.getListeQuestionsReponses().iterator(); iterator.hasNext();) {
			QuestionReponse questionReponse = iterator.next();
			if (questionReponse.getId().equals(idQr)) {
				iterator.remove();
			}
		}
		return this.saveQcm(qcm);
	}

	@Override
	public Qcm addReponse(Qcm qcm, Long idQr) {
		for (QuestionReponse questionReponse : qcm.getListeQuestionsReponses()) {
			if (questionReponse.getId().equals(idQr)) {
				Reponse reponse = new Reponse("A saisir");
				questionReponse.getListeReponses().add(reponse);
				break;
			}
		}
		return this.saveQcm(qcm);
	}

	@Override
	public Qcm deleteReponse(Qcm qcm, Long idQr, Long idReponse) {
		for (QuestionReponse questionReponse : qcm.getListeQuestionsReponses()) {
			for (Iterator<Reponse> iterator = questionReponse.getListeReponses().iterator(); iterator.hasNext();) {
				Reponse reponse = iterator.next();
				if (reponse.getId().equals(idReponse)) {
					iterator.remove();
				}
			}
		}
		return this.saveQcm(qcm);
	}

}
