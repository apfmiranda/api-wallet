package br.com.apfmiranda.wallet.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.apfmiranda.wallet.model.entity.WalletItem;
import br.com.apfmiranda.wallet.repository.WalletItemRepository;
import br.com.apfmiranda.wallet.service.WalletItemService;
import br.com.apfmiranda.wallet.util.enums.TypeEnum;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 2 de abr de 2021
 *
 */
@Service
public class WalletItemServiceImpl implements WalletItemService {
	
	@Autowired
	WalletItemRepository repository;
	
	
	@Value("${pagination.items_per_page}")
	private int itemsPerPage;

	@Override
	public WalletItem save(WalletItem walletItem) {
		return repository.save(walletItem);
	}

	@Override
	public Page<WalletItem> findBetweenDates(long wallet, Date ini, Date and, int page) {
		PageRequest pg = PageRequest.of(page, itemsPerPage);
		return repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(wallet, ini, and, pg);
	}

	@Override
	public List<WalletItem> findByWalletAndType(long wallet, TypeEnum en) {
		return repository.findByWalletIdAndType(wallet, en);
	}

	@Override
	public BigDecimal sumByWalletId(long wallet) {
		return repository.sumByWalletId(wallet);
	}

	@Override
	public Optional<WalletItem> findById(long id) {		
		return repository.findById(id);
	}

	@Override
	public void deleteById(Long walletItemId) {
		// TODO Auto-generated method stub
		
	}

}
