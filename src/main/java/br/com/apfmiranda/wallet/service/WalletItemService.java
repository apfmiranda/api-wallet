package br.com.apfmiranda.wallet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import br.com.apfmiranda.wallet.model.entity.WalletItem;
import br.com.apfmiranda.wallet.util.enums.TypeEnum;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 2 de abr de 2021
 *
 */
public interface WalletItemService {

	WalletItem save(WalletItem walletItem);

	Page<WalletItem> findBetweenDates(long wallet, Date ini, Date and, int i);

	List<WalletItem> findByWalletAndType(long wallet, TypeEnum en);

	BigDecimal sumByWalletId(long l);

	Optional<WalletItem> findById(long anyLong);

	void deleteById(Long walletItemId);


}
