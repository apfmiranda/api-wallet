package br.com.apfmiranda.wallet.service;

import java.util.Optional;

import br.com.apfmiranda.wallet.model.entity.User;

public interface UserService {

	User save(User user);
	
	Optional<User> findByEmail(String email);

}
