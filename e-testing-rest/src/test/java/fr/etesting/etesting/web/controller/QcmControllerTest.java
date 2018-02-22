package fr.etesting.etesting.web.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.InputStream;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.etesting.etesting.exception.QcmNotFoundException;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.service.IQcmService;
import fr.etesting.etesting.service.implementation.XmlConverter;

@RunWith(SpringRunner.class)
@WebMvcTest(QcmController.class)
public class QcmControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext ctx;

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
		mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).alwaysDo(MockMvcResultHandlers.print()).build();
		InputStream fileToUpload = new ClassPathResource(fileName).getInputStream();
		qcmXml = new MockMultipartFile("xml", fileName, MediaType.APPLICATION_XML.getType(), fileToUpload);
	}

	@Test
	@WithMockUser
	public void testConvertXmlToObject() throws Exception {

		when(xmlConverter.convertFromXMLToObject(any(MockMultipartFile.class))).thenReturn(new Qcm());
		when(qcmServiceImpl.saveQcm(any(Qcm.class))).thenReturn(new Qcm());

		ResultActions result = mockMvc.perform(fileUpload(QcmController.URL_TO_CONVERT_XML_TO_QCM).file(qcmXml));
		result.andExpect(status().isOk());

		verify(xmlConverter).convertFromXMLToObject(any(MockMultipartFile.class));
		verify(qcmServiceImpl).saveQcm(any(Qcm.class));
	}

	@Test
	public void testConvertQcmToXml() throws Exception {
		Long idQcm = 1L;

		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenReturn(new Qcm());

		ResultActions result = mockMvc.perform(get(QcmController.URL_TO_CONVERT_QCM_TO_XML, idQcm));
		result.andExpect(status().isOk());

		verify(qcmServiceImpl).findQcmById(eq(idQcm));

	}

	@Test
	public void testconvertQcmToXmlQcmNotFoundException() throws Exception {

		Long idQcm = 1L;

		when(qcmServiceImpl.findQcmById(eq(idQcm))).thenThrow(new QcmNotFoundException());

		ResultActions result = mockMvc.perform(get(QcmController.URL_TO_CONVERT_QCM_TO_XML, idQcm));
		result.andExpect(status().isNotFound());

		verify(qcmServiceImpl).findQcmById(eq(idQcm));
	}

}
