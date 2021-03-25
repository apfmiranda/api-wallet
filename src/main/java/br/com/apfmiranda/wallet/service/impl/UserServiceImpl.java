package br.com.apfmiranda.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apfmiranda.wallet.entity.User;
import br.com.apfmiranda.wallet.repository.UserRepository;
import br.com.apfmiranda.wallet.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return repository.findByEmailEquals(email);
	}

}
