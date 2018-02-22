package fr.etesting.etesting.service.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.etesting.etesting.exception.QcmNotFoundException;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.repository.QcmRepository;

@RunWith(MockitoJUnitRunner.class)
public class QcmServiceImplTest {
	@Mock
	private QcmRepository qcmRepository;
	
	@InjectMocks
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


}
