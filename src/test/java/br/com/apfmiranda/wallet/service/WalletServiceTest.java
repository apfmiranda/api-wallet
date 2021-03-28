package br.com.apfmiranda.wallet.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.apfmiranda.wallet.model.entity.Wallet;
import br.com.apfmiranda.wallet.repository.WalletRepository;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 27 de mar de 2021
 *
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class WalletServiceTest {
	
	@MockBean
	WalletRepository repository;
	
	@Autowired
	WalletService service;

	@BeforeAll
	void setUp() throws Exception {
		BDDMockito.given(repository.save(Mockito.any(Wallet.class))).willReturn(new Wallet());
	}

	@Test
	void testSave() {
		Wallet w = new Wallet();
		w.setName("Carteira teste");
		w.setValue(new BigDecimal(10.10));
		
		Wallet response = service.save(w);
		
		assertNotNull(response);
	}

}
