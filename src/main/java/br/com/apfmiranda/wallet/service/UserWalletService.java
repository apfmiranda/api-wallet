package br.com.apfmiranda.wallet.service;

import br.com.apfmiranda.wallet.model.entity.UserWallet;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 27 de mar de 2021
 *
 */
public interface UserWalletService {
	
	UserWallet save(UserWallet userWallet);

}
