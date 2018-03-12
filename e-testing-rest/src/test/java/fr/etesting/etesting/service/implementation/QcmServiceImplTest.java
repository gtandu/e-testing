package fr.etesting.etesting.service.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import fr.etesting.etesting.exception.QcmNotFoundException;
import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.model.QuestionReponse;
import fr.etesting.etesting.model.Reponse;
import fr.etesting.etesting.repository.QcmRepository;
import fr.etesting.etesting.service.IAccountService;
import fr.etesting.etesting.service.mail.PublishMarkMail;

@RunWith(MockitoJUnitRunner.class)
public class QcmServiceImplTest {
	@Mock
	private QcmRepository qcmRepository;
	
	@Mock
	private IAccountService accountService;
	
	@Mock
	private PublishMarkMail publishMarkMail;
	
	@InjectMocks
	@Spy
	private QcmServiceImpl qcmServiceImpl;

	@Test
	public void testSaveQcm() throws Exception {
		

		Qcm qcm = new Qcm();
		when(qcmRepository.save(any(Qcm.class))).thenReturn(new Qcm());
		
		Qcm saveQcm = qcmServiceImpl.saveQcm(qcm);
		
		assertThat(saveQcm).isNotNull();
		
		verify(qcmRepository).save(any(Qcm.class));
	}

	@Test
	public void testFindQcmById() throws Exception {
		Long idQcm = 1L;
		
		when(qcmRepository.findById(idQcm)).thenReturn(Optional.of(new Qcm()));
		
		Qcm qcm = qcmServiceImpl.findQcmById(idQcm);
		
		assertThat(qcm).isNotNull();
		
		verify(qcmRepository).findById(idQcm);
		
	}
	
	@Test(expected=QcmNotFoundException.class)
	public void testFindQcmByIdThrowQcmNotFoundException() throws Exception {
		Long idQcm = 1L;
		
		when(qcmRepository.findById(idQcm)).thenReturn(Optional.empty());
		
		qcmServiceImpl.findQcmById(idQcm);
		
		verify(qcmRepository).findById(idQcm);
		
	}

	@Test
	public void testFindAllQcm() throws Exception {
		when(qcmRepository.findAll()).thenReturn(Arrays.asList(new Qcm()));
		
		List<Qcm> allQcm = qcmServiceImpl.findAllQcm();
		
		assertThat(allQcm).isNotNull();
		assertThat(allQcm).isNotEmpty();
		
		verify(qcmRepository).findAll();
	}

	@Test
	public void testCorrectQcm() throws Exception {
		Qcm qcm = buildQcm();
		Long idQcm = 1L;
		String mail = "admin@test.fr";
		
		when(accountService.findByMail(eq(mail))).thenReturn(new Account());
		doNothing().when(qcmServiceImpl).sendQcmResult(anyString(), anyString(), anyString(), anyDouble());
		
		Qcm correctQcm = qcmServiceImpl.correctQcm(idQcm, qcm, mail);
		
		assertThat(correctQcm).isNotNull();
		assertThat(correctQcm.getNoteFinale()).isEqualTo(20);
		
		verify(accountService).findByMail(eq(mail));
		verify(qcmServiceImpl).sendQcmResult(anyString(), anyString(), anyString(), anyDouble());
	}

	private Qcm buildQcm() {
		Qcm qcm = new Qcm();
		QuestionReponse questionReponse = new QuestionReponse("Test");
		Reponse reponse = new Reponse("Test Valide");
		reponse.setBonneReponse(true);
		reponse.setPoints(3);
		reponse.setRepondu(true);
		questionReponse.setListeReponses(Arrays.asList(reponse));
		List<QuestionReponse> listeQuestionsReponses = Arrays.asList(questionReponse);
		qcm.setListeQuestionsReponses(listeQuestionsReponses );
		qcm.setTotalPts(3);
		return qcm;
	}

	@Test
	public void testUpdatePts() throws Exception {
		Qcm qcm = buildQcm();
		qcm.setTotalPts(0);
		
		Qcm qcmUpdatedPts = qcmServiceImpl.updatePts(qcm);
		
		assertThat(qcmUpdatedPts).isNotNull();
		assertThat(qcmUpdatedPts.getTotalPts()).isEqualTo(3);
	}

	@Test
	public void testSendQcmResult() throws Exception {
		String studentFirstName = "Admin" ;
		String studentLastName = "Test" ;
		double markQcm = 15.7;
		String qcmName = "Qcm Test";
		
		when(accountService.findAllAdmin()).thenReturn(Arrays.asList(new Account(), new Account()));
		when(publishMarkMail.sendMailPublishMarks(eq(studentFirstName), eq(studentLastName), anyString(), eq(qcmName), eq(markQcm))).thenReturn(true);
		
		qcmServiceImpl.sendQcmResult(studentFirstName, studentLastName, qcmName, markQcm);
		
		verify(accountService).findAllAdmin();
		verify(publishMarkMail, times(2)).sendMailPublishMarks(eq(studentFirstName), eq(studentLastName), anyString(), eq(qcmName), eq(markQcm));
	}

	@Test
	public void testAddQuestionReponse() throws Exception {
		Qcm qcm = mock(Qcm.class);		
		doReturn(qcm).when(qcmServiceImpl).saveQcm(any(Qcm.class));
		
		qcmServiceImpl.addQuestionReponse(qcm);
		
		verify(qcm).getListeQuestionsReponses();
		verify(qcmServiceImpl).saveQcm(any(Qcm.class));
		
	}

	@Test
	public void testDeleteQuestionReponse() throws Exception {
		Long idQr = 1L;
		Qcm qcm = mock(Qcm.class);
		QuestionReponse questionReponse = new QuestionReponse("A tester");
		questionReponse.setId(idQr);
		ArrayList<QuestionReponse> listQuestionsReponses = new ArrayList<>();
		listQuestionsReponses.add(questionReponse);
		
		when(qcm.getListeQuestionsReponses()).thenReturn(listQuestionsReponses);
		doReturn(qcm).when(qcmServiceImpl).saveQcm(any(Qcm.class));

		qcmServiceImpl.deleteQuestionReponse(qcm, idQr);
		
		verify(qcm, times(2)).getListeQuestionsReponses();
		verify(qcmServiceImpl).saveQcm(any(Qcm.class));
	}

	@Test
	public void testAddReponse() throws Exception {
		Long idQr = 1L;
		Qcm qcm = mock(Qcm.class);
		
		doReturn(qcm).when(qcmServiceImpl).saveQcm(any(Qcm.class));
		
		qcmServiceImpl.addReponse(qcm, idQr);
		
		verify(qcm).getListeQuestionsReponses();
		verify(qcmServiceImpl).saveQcm(any(Qcm.class));
	}

	@Test
	public void testDeleteReponse() throws Exception {
		Long idQr = 1L;
		Long idReponse = 1L;
		Qcm qcm = mock(Qcm.class);
		QuestionReponse questionReponse = new QuestionReponse("A tester");
		Reponse reponse = new Reponse();
		reponse.setId(idReponse);
		ArrayList<Reponse> listeReponses = new ArrayList<Reponse>();
		listeReponses.add(reponse);
		questionReponse.setListeReponses(listeReponses);
		
		when(qcm.getListeQuestionsReponses()).thenReturn(Arrays.asList(questionReponse));
		doReturn(qcm).when(qcmServiceImpl).saveQcm(any(Qcm.class));
		
		qcmServiceImpl.deleteReponse(qcm, idQr, idReponse);
		
		verify(qcm).getListeQuestionsReponses();
		verify(qcmServiceImpl).saveQcm(any(Qcm.class));
	}


}
