package br.com.apfmiranda.wallet.service;

import br.com.apfmiranda.wallet.model.entity.Wallet;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 26 de mar de 2021
 *
 */
public interface WalletService {
	
	Wallet save(Wallet wallet);

}
