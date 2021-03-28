package br.com.apfmiranda.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.apfmiranda.wallet.model.entity.Wallet;


/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 27 de mar de 2021
 *
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class WalletRepositoryTest {
	
	private static final int _100 = 100;
	private static final String CARTEIRA_PRINCIPAL = "Carteira principal";
	@Autowired
	WalletRepository repository;


	@Test
	void testSave() {
		Wallet w = new Wallet();
		w.setName(CARTEIRA_PRINCIPAL);
		w.setValue(new BigDecimal(_100));
		
		Wallet response = repository.save(w);
		
		assertNotNull(response);	
		assertEquals(response.getName(), CARTEIRA_PRINCIPAL, "Nome de carteira diferente do esperado");
		assertEquals(response.getValue(), new BigDecimal(_100),"Valor da carteira diferente do esperado" );
	}

}
