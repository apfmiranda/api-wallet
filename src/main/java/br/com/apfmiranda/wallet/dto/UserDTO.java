package br.com.apfmiranda.wallet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.apfmiranda.wallet.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 25 de mar de 2021
 *
 */
@Data
@NoArgsConstructor
public class UserDTO {
	
	private Long id;
	@NotNull
	@Length(min = 6, message = "A senha deve conter no minimo 6 caracteres")
	private String password;
	@Length(min = 3, max = 50, message = "O nome de conter entre 3 e 50 caracteres")
	private String name;
	@Email(message = "Email inválido")
	private String email;
	
	public UserDTO(User user) {
		this.email = user.getEmail();
		this.name = user.getName();
		this.password = user.getPassword();
	}
	
	public User toEntity() {
		User u = new User();
		u.setEmail(this.email);
		u.setName(this.name);
		u.setPassword(this.password);
		return u;
	}

}
