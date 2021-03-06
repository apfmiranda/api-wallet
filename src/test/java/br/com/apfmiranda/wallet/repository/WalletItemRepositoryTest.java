package br.com.apfmiranda.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import br.com.apfmiranda.wallet.model.entity.Wallet;
import br.com.apfmiranda.wallet.model.entity.WalletItem;
import br.com.apfmiranda.wallet.util.enums.TypeEnum;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 28 de mar de 2021
 *
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class WalletItemRepositoryTest {
	
	private static final Date DATE = new Date();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Conta de luz";
	private static final BigDecimal VALUE = BigDecimal.valueOf(65);
	private Long savedWalletItemId = null;
	private Long savedWalletId = null;
	
	@Autowired
	WalletItemRepository repository;
	
	@Autowired
	WalletRepository walletRepository;
	
	
	@BeforeEach
	void setUp() throws Exception {
		Wallet w = new Wallet();
		w.setName("Carteira teste");
		w.setValue(VALUE);
		walletRepository.save(w);
		
		WalletItem wi = new WalletItem(1L, w, DATE, TYPE, DESCRIPTION, VALUE);
		repository.save(wi);
		
		savedWalletId = w.getId();
		savedWalletItemId = wi.getId();
		
	}
	
	
	@AfterAll
	public void tearDown() {
		repository.deleteAll();
		walletRepository.deleteAll();
	}
	
	
	@Test
	public void testSave() {
		Wallet w = new Wallet();
		w.setName("Carteira teste");
		w.setValue(BigDecimal.valueOf(250));
		
		walletRepository.save(w);
		
		WalletItem wi = new WalletItem(1L, w, DATE, TYPE, DESCRIPTION, VALUE);
		
		WalletItem response = repository.save(wi);
		
		assertNotNull(response);
		assertEquals(response.getType(), TYPE);
		assertEquals(response.getDescription(), DESCRIPTION);
		assertEquals(response.getValue(), VALUE);
		assertEquals(response.getWallet().getId(), w.getId());
		
	}
	
	
	@Test
	public void testSaveInvalidWalletItem() {
		assertThrows(ConstraintViolationException.class, () -> {
			WalletItem wi = new WalletItem(null, null, DATE, null, DESCRIPTION, null);
			
			repository.save(wi);
		});
		
	}
	
	
	@Test
	public void testUpdate() {
		Optional<WalletItem> wi = repository.findById(savedWalletItemId);
		
		String description = "Descri????o alterada";
		
		WalletItem changed = wi.get();
		changed.setDescription(description);
		
		repository.save(changed);
		
		Optional<WalletItem> newWalletItem = repository.findById(savedWalletItemId);
		
		assertEquals(description, newWalletItem.get().getDescription());
	}
	
	@Test
	public void testDeleteWalletItem() {
		Optional<Wallet> wallet = walletRepository.findById(savedWalletId);
		WalletItem wi = new WalletItem(null, wallet.get(), DATE, TYPE, DESCRIPTION, VALUE);
		
		repository.save(wi);
		
		repository.deleteById(wi.getId());
		
		Optional<WalletItem> response = repository.findById(wi.getId());
		
		assertFalse(response.isPresent());
	}
	
	@Test
	public void testFindBetweenDates() {
		Optional<Wallet> w = walletRepository.findById(savedWalletId);
		
		LocalDateTime localDateTime = DATE.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		Date currentDatePlusFiveDays = Date.from(localDateTime.plusDays(5).atZone(ZoneId.systemDefault()).toInstant());
		Date currentDatePlusSevenDays = Date.from(localDateTime.plusDays(7).atZone(ZoneId.systemDefault()).toInstant());

        
		repository.save(new WalletItem(null, w.get(), currentDatePlusFiveDays, TYPE, DESCRIPTION, VALUE));
		repository.save(new WalletItem(null, w.get(), currentDatePlusSevenDays, TYPE, DESCRIPTION, VALUE));
		
		PageRequest pg = PageRequest.of(0, 10); 
		Page<WalletItem> response = repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(savedWalletId, DATE, currentDatePlusFiveDays, pg);
		
		assertEquals(response.getContent().size(), 2);
		assertEquals(response.getTotalElements(), 2);
		assertEquals(response.getContent().get(0).getWallet().getId(), savedWalletId);
	}
	
	@Test
	public void testFindByType() {
		List<WalletItem> response = repository.findByWalletIdAndType(savedWalletId, TYPE);
		
		assertEquals(response.size(), 1);
		assertEquals(response.get(0).getType(), TYPE);
	}
	
	@Test
	public void testFindByTypeSd() {
		
		Optional<Wallet> w = walletRepository.findById(savedWalletId);
		
		repository.save(new WalletItem(null, w.get(), DATE, TypeEnum.SD, DESCRIPTION, VALUE));
		
		List<WalletItem> response = repository.findByWalletIdAndType(savedWalletId, TypeEnum.SD);
		
		assertEquals(response.size(), 1);
		assertEquals(response.get(0).getType(), TypeEnum.SD);
	}

	@Test
	public void testSumByWallet() {
		Optional<Wallet> w = walletRepository.findById(savedWalletId);
		
		WalletItem wi = repository.save(new WalletItem(null, w.get(), DATE, TYPE, DESCRIPTION, BigDecimal.valueOf(150.80)));
		
		BigDecimal valorEsperado = wi.getValue().add(VALUE);
		
		BigDecimal response = repository.sumByWalletId(savedWalletId);
		
		assertEquals(response.compareTo(valorEsperado), 0);
	}
	
	

}
