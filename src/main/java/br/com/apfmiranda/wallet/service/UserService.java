package br.com.apfmiranda.wallet.service;

import java.util.Optional;

import br.com.apfmiranda.wallet.model.entity.User;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 26 de mar de 2021
 *
 */
public interface UserService {

	User save(User user);
	
	Optional<User> findByEmail(String email);

}
