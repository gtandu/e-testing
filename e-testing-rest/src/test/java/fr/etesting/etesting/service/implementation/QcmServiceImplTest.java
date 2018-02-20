package fr.etesting.etesting.service.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

}
