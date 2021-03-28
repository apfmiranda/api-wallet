package br.com.apfmiranda.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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

import br.com.apfmiranda.wallet.model.entity.Wallet;
import br.com.apfmiranda.wallet.service.WalletService;


/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 27 de mar de 2021
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class WalletControllerTest {
	
	private static final String NAME = "Usuario teste";
	private static final BigDecimal VALUE = new BigDecimal(100.50);
	private static final String URL = "/wallet";
	private static final Long ID = 1L;
	
	
	@MockBean
	WalletService service;
	
	@Autowired
	MockMvc mvc;

	@Test
	void testSave() throws Exception {
		BDDMockito.given(service.save(Mockito.any(Wallet.class))).willReturn(getMockWallet());
		
		mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, VALUE))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.id").value(ID))
		.andExpect(jsonPath("$.data.name").value(NAME))
		.andExpect(jsonPath("$.data.value").value(VALUE));;
	}

	private String getJsonPayload(Long id, String name, BigDecimal value) throws JsonProcessingException {
		Wallet w = new Wallet();
		w.setId(id);
		w.setName(name);
		w.setValue(value);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(w);
	}
	
	private Wallet getMockWallet() {
		Wallet w = new Wallet();
		w.setId(ID);
		w.setName(NAME);
		w.setValue(VALUE);
		
		return w;
	}

}
