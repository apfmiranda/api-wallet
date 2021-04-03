package br.com.apfmiranda.wallet.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	Page<WalletItem> findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual (			         
			Long wallet, 
			Date init,
			Date end, 
			Pageable page);

	List<WalletItem> findByWalletIdAndType(Long wallet, TypeEnum type);

	
	@Query(value = "select sum(value) from WalletItem wi where wi.wallet.id = :wallet")
	BigDecimal sumByWalletId(@Param("wallet") Long wallet);

}
