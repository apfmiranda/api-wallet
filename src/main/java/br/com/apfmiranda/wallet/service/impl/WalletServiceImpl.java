package br.com.apfmiranda.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.apfmiranda.wallet.model.entity.Wallet;
import br.com.apfmiranda.wallet.repository.WalletRepository;
import br.com.apfmiranda.wallet.service.WalletService;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 26 de mar de 2021
 *
 */
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	private WalletRepository repository;

	@Override
	public Wallet save(Wallet wallet) {
		return repository.save(wallet);
	}

}
