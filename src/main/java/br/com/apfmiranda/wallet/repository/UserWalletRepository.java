package br.com.apfmiranda.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apfmiranda.wallet.model.entity.UserWallet;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 27 de mar de 2021
 *
 */
@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet, Long>{

}
