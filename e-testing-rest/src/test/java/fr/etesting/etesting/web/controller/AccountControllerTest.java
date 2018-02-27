package fr.etesting.etesting.web.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.etesting.etesting.model.Account;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.service.IQcmService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	
	@MockBean
	private IQcmService qcmServiceImpl;
	
	@InjectMocks
	private AccountController accountController;

	@Autowired
	private MockMvc mockMvc;

	@WithMockUser(username="test@mail.fr")
	@Test
	public void testCorrectQcm() throws Exception {
		Long idQcm = 1L;
		ObjectMapper objectMapper = new ObjectMapper();
		when(qcmServiceImpl.correctQcm(eq(idQcm), any(Qcm.class), anyString())).thenReturn(new Account());

		ResultActions result = mockMvc.perform(post(AccountController.URL_CORRECT_QCM, idQcm).content(objectMapper.writeValueAsString(new Qcm())).contentType(MediaType.APPLICATION_JSON_UTF8));
		
		result.andExpect(status().isOk());
		result.andDo(print());
		
		verify(qcmServiceImpl).correctQcm(eq(idQcm), any(Qcm.class), anyString());
	}

}
