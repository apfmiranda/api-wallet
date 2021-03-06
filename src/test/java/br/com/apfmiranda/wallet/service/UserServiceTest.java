package br.com.apfmiranda.wallet.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.apfmiranda.wallet.model.entity.User;
import br.com.apfmiranda.wallet.repository.UserRepository;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 25 de mar de 2021
 *
 */
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {

	@MockBean
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	@Test
	public void testFindByEmail() {
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new User()));
	
		Optional<User> user = service.findByEmail("email@teste.com.br");
		
		assertTrue(user.isPresent());
	}
	
	@Test
	void testSave() {
		BDDMockito.given(repository.save(Mockito.any(User.class))).willReturn(new User());
		
		User u = new User();
		u.setName("usuario teste");
		u.setEmail("email@teste.com.br");
		u.setPassword("123456");
		
		User response = service.save(u);
		
		assertNotNull(response);
	}
	
}
