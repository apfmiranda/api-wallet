package br.com.apfmiranda.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apfmiranda.wallet.model.entity.UserWallet;
import br.com.apfmiranda.wallet.repository.UserWalletRepository;
import br.com.apfmiranda.wallet.service.UserWalletService;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 27 de mar de 2021
 *
 */
@Service
public class UserWalletServiceImpl implements UserWalletService{
	
	@Autowired
	private UserWalletRepository repository;

	@Override
	public UserWallet save(UserWallet userWallet) {
		return repository.save(userWallet);
	}

}
