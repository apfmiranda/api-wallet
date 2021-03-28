package br.com.apfmiranda.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.apfmiranda.wallet.model.entity.Wallet;
import br.com.apfmiranda.wallet.model.entity.WalletItem;
import br.com.apfmiranda.wallet.util.enums.TypeEnum;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class WalletItemRepositoryTest {
	
	private static final Date DATE = new Date();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Conta de luz";
	private static final BigDecimal VALUE = BigDecimal.valueOf(78);
	
	@Autowired
	WalletItemRepository repository;
	
	@Autowired
	WalletRepository walletRepository;
	
	@Test
	public void testSave() {
		Wallet w = new Wallet();
		w.setName("Carteira teste");
		w.setValue(BigDecimal.valueOf(45));
		
		walletRepository.save(w);
		
		WalletItem wi = new WalletItem(1L, w, DATE, TYPE, DESCRIPTION, VALUE);
		
		WalletItem response = repository.save(wi);
		
		assertNotNull(response);
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getDescription(), DESCRIPTION);
		assertEquals(response.getValue(), VALUE);
		assertEquals(response.getWallet().getId(), w.getId());
		
	}

}
