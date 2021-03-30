package br.com.apfmiranda.wallet.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apfmiranda.wallet.model.entity.WalletItem;
import br.com.apfmiranda.wallet.util.enums.TypeEnum;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 28 de mar de 2021
 *
 */
@Repository
public interface WalletItemRepository extends JpaRepository<WalletItem, Long>{

	Page<WalletItem> findByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(
			Long wallet, 
			Date init,
			Date end, 
			PageRequest page);

	List<WalletItem> findByWalletIdAndType(Long wallet, TypeEnum type);

}
