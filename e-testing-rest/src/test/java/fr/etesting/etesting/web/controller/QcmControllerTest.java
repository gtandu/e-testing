package fr.etesting.etesting.web.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.etesting.etesting.exception.QcmNotFoundException;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.service.IQcmService;
import fr.etesting.etesting.service.implementation.XmlConverter;

@RunWith(SpringRunner.class)
@WebMvcTest(QcmController.class)
public class QcmControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IQcmService qcmServiceImpl;

	@MockBean
	private XmlConverter xmlConverter;

	@InjectMocks
	private QcmController qcmController;

	private String fileName = "questionnaire.xml";

	private MockMultipartFile qcmXml;

	@Before
	public void init() throws IOException {
		InputStream fileToUpload = new ClassPathResource(fileName).getInputStream();
		qcmXml = new MockMultipartFile("file", fileName, MediaType.APPLICATION_XML.getType(), fileToUpload);
	}

	@Test
	@WithMockUser
	public void testConvertXmlToObject() throws Exception {

		when(xmlConverter.convertFromXMLToObject(any(MockMultipartFile.class))).thenReturn(new Qcm());
		when(qcmServiceImpl.saveQcm(any(Qcm.class))).thenReturn(new Qcm());

		ResultActions result = mockMvc.perform(fileUpload(QcmController.URL_QCM+"/xml").file(qcmXml));
		result.andExpect(status().isOk());

		verify(xmlConverter).convertFromXMLToObject(any(MockMultipartFile.class));
		verify(qcmServiceImpl).saveQcm(any(Qcm.class));
	}

	@Test
	@WithMockUser
	public void testConvertQcmToXml() throws Exception {
		Long idQcm = 1L;

		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenReturn(new Qcm());

		ResultActions result = mockMvc.perform(get(QcmController.URL_QCM_BY_ID+"/xml", idQcm));
		result.andExpect(status().isOk());
		result.andExpect(content().contentType("application/xml;charset=UTF-8"));


		verify(qcmServiceImpl).findQcmById(eq(idQcm));

	}

	@Test
	@WithMockUser
	public void testconvertQcmToXmlQcmNotFoundException() throws Exception {

		Long idQcm = 1L;

		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenThrow(new QcmNotFoundException());

		ResultActions result = mockMvc.perform(get(QcmController.URL_QCM_BY_ID+"/xml", idQcm));
		result.andExpect(status().isNotFound());

		verify(qcmServiceImpl).findQcmById(eq(idQcm));
	}

	@Test
	@WithMockUser
	public void testGetQcm() throws Exception {
		Long idQcm = 1L;

		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenReturn(new Qcm());

		ResultActions result = mockMvc.perform(get(QcmController.URL_QCM_BY_ID, idQcm));
		result.andExpect(status().isOk());
		result.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));


		verify(qcmServiceImpl).findQcmById(eq(idQcm));
	}
	
	@Test
	@WithMockUser
	public void testGetQcmNotFound() throws Exception {
		Long idQcm = 1L;

		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenThrow(new QcmNotFoundException());

		ResultActions result = mockMvc.perform(get(QcmController.URL_QCM_BY_ID, idQcm));
		result.andExpect(status().isNotFound());

		verify(qcmServiceImpl).findQcmById(eq(idQcm));
	}

	@Test
	@WithMockUser
	public void testSavedQcm() throws Exception {
		Long idQcm = 1L;
		Qcm qcm = new Qcm();
		ObjectMapper objectMapper = new ObjectMapper();
		when(qcmServiceImpl.updatePts(any(Qcm.class))).thenReturn(new Qcm());
		when(qcmServiceImpl.saveQcm(any(Qcm.class))).thenReturn(new Qcm());
		
		ResultActions result = mockMvc.perform(put(QcmController.URL_QCM_BY_ID, idQcm).content(objectMapper.writeValueAsString(qcm)).contentType(MediaType.APPLICATION_JSON_UTF8));
		result.andExpect(status().isCreated());
		
		verify(qcmServiceImpl).updatePts(any(Qcm.class));
		verify(qcmServiceImpl).saveQcm(any(Qcm.class));
	}

	@Test
	@WithMockUser(username="admin@test.fr")
	public void testCorrectQcm() throws Exception {
		Long idQcm = 1L;
		Qcm qcm = new Qcm();
		ObjectMapper objectMapper = new ObjectMapper();
		when(qcmServiceImpl.correctQcm(eq(idQcm), any(Qcm.class), anyString())).thenReturn(new Qcm());
		
		ResultActions result = mockMvc.perform(post(QcmController.URL_QCM_BY_ID, idQcm).content(objectMapper.writeValueAsString(qcm)).contentType(MediaType.APPLICATION_JSON_UTF8));
		result.andExpect(status().isOk());
		
		verify(qcmServiceImpl).correctQcm(eq(idQcm), any(Qcm.class), anyString());
		
	}

	@Test
	@WithMockUser
	public void testResetQcm() throws Exception {
		Long idQcm = 1L;
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenReturn(new Qcm());
		when(qcmServiceImpl.resetQcm(any(Qcm.class))).thenReturn(new Qcm());
		
		ResultActions result = mockMvc.perform(patch(QcmController.URL_QCM_BY_ID, idQcm));
		result.andExpect(status().isOk());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
		verify(qcmServiceImpl).resetQcm(any(Qcm.class));
	}
	
	@Test
	@WithMockUser
	public void testResetQcmNotFoud() throws Exception {
		Long idQcm = 1L;
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenThrow(new QcmNotFoundException());
		
		ResultActions result = mockMvc.perform(patch(QcmController.URL_QCM_BY_ID, idQcm));
		result.andExpect(status().isNotFound());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
	}

	@Test
	@WithMockUser
	public void testGetAllQcm() throws Exception {
		when(qcmServiceImpl.findAllQcm()).thenReturn(Arrays.asList(new Qcm()));
		
		ResultActions result = mockMvc.perform(get(QcmController.URL_QCM));
		result.andExpect(status().isOk());
		
		verify(qcmServiceImpl).findAllQcm();
	}

	@Test
	@WithMockUser
	public void testAddQuestionReponse() throws Exception {
		Long idQcm = 1L;
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenReturn(new Qcm());
		when(qcmServiceImpl.addQuestionReponse(any(Qcm.class))).thenReturn(new Qcm());
		
		ResultActions result = mockMvc.perform(put(QcmController.URL_QUESTION_REPONSE, idQcm));
		result.andExpect(status().isOk());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
		verify(qcmServiceImpl).addQuestionReponse(any(Qcm.class));
	}
	
	@Test
	@WithMockUser
	public void testAddQuestionReponseQcmNotFoundException() throws Exception {
		Long idQcm = 1L;
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenThrow(new QcmNotFoundException());
		
		ResultActions result = mockMvc.perform(put(QcmController.URL_QUESTION_REPONSE, idQcm));
		result.andExpect(status().isNotFound());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
	}

	@Test
	@WithMockUser
	public void testDeleteQuestionReponse() throws Exception {
		Long idQcm = 1L;
		Long idQr = 1L;
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenReturn(new Qcm());
		when(qcmServiceImpl.deleteQuestionReponse(any(Qcm.class), eq(idQr))).thenReturn(new Qcm());
		
		ResultActions result = mockMvc.perform(delete(QcmController.URL_QUESTION_REPONSE_BY_ID, idQcm, idQr));
		result.andExpect(status().isOk());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
		verify(qcmServiceImpl).deleteQuestionReponse(any(Qcm.class), eq(idQr));
	}
	
	@Test
	@WithMockUser
	public void testDeleteQuestionReponseQcmNotFoundException() throws Exception {
		Long idQcm = 1L;
		Long idQr = 1L;
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenThrow(new QcmNotFoundException());
		
		ResultActions result = mockMvc.perform(delete(QcmController.URL_QUESTION_REPONSE_BY_ID, idQcm, idQr));
		result.andExpect(status().isNotFound());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
	}

	@Test
	@WithMockUser
	public void testAddReponse() throws Exception {
		Long idQcm = 1L;
		Long idQr = 1L;
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenReturn(new Qcm());
		when(qcmServiceImpl.addReponse(any(Qcm.class), eq(idQr))).thenReturn(new Qcm());
		
		ResultActions result = mockMvc.perform(put(QcmController.URL_REPONSE, idQcm, idQr));
		result.andExpect(status().isOk());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
		verify(qcmServiceImpl).addReponse(any(Qcm.class), eq(idQr));
	}
	
	@Test
	@WithMockUser
	public void testAddReponseQcmNotFoundException() throws Exception {
		Long idQcm = 1L;
		Long idQr = 1L;
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenThrow(new QcmNotFoundException());
		
		ResultActions result = mockMvc.perform(put(QcmController.URL_REPONSE, idQcm, idQr));
		result.andExpect(status().isOk());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
	}

	@Test
	@WithMockUser
	public void testDeleteReponse() throws Exception {
		Long idQcm = 1L;
		Long idQr = 1L;
		Long idReponse = 1L;
		
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenReturn(new Qcm());
		when(qcmServiceImpl.deleteReponse(any(Qcm.class), eq(idQr), eq(idReponse))).thenReturn(new Qcm());
		
		ResultActions result = mockMvc.perform(delete(QcmController.URL_REPONSE_BY_ID, idQcm, idQr, idReponse));
		result.andExpect(status().isOk());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
		verify(qcmServiceImpl).deleteReponse(any(Qcm.class), eq(idQr), eq(idReponse));
	}
	
	@Test
	@WithMockUser
	public void testDeleteReponseQcmNotFoundException() throws Exception {
		Long idQcm = 1L;
		Long idQr = 1L;
		Long idReponse = 1L;
		
		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenThrow(new QcmNotFoundException());
		
		ResultActions result = mockMvc.perform(delete(QcmController.URL_REPONSE_BY_ID, idQcm, idQr, idReponse));
		result.andExpect(status().isNotFound());
		
		verify(qcmServiceImpl).findQcmById(eq(idQcm));
	}


}
