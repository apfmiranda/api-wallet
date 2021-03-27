package br.com.apfmiranda.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apfmiranda.wallet.model.entity.User;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 25 de mar de 2021
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmailEquals(String email);

}
