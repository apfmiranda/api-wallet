package br.com.apfmiranda.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.apfmiranda.wallet.model.dto.UserDTO;
import br.com.apfmiranda.wallet.model.entity.User;
import br.com.apfmiranda.wallet.service.UserService;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 25 de mar de 2021
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
//@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {
	
	private static final String NAME = "Usuario teste";
	private static final String PASSWORD = "123456";
	private static final String EMAIL = "email@teste.com.br";
	private static final String URL = "/user";
	private static final Long ID = 1L;

	@MockBean
	UserService service;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSave() throws Exception {
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, EMAIL, NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.name").value(NAME))
		.andExpect(jsonPath("$.data.password").doesNotExist())
		.andExpect(jsonPath("$.data.email").value(EMAIL));
		
	}
	
	@Test
	public void testSaveInvalidUser() throws Exception {
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, "email", NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.erros[0]").value("Email inválido"));
		
	}
	
	
	public User getMockUser() {
		User u = new User();
		u.setId(ID);
		u.setName(NAME);
		u.setPassword(PASSWORD);
		u.setEmail(EMAIL);
		
		return u;
	}
	
	public String getJsonPayload(Long id, String email, String name, String password) throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setPassword(password);
		dto.setEmail(email);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
		
	}

}
