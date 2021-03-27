package br.com.apfmiranda.wallet.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.apfmiranda.wallet.model.entity.User;
import br.com.apfmiranda.wallet.util.Bcrypt;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
		this.id = user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
	}
	
	public User toEntity() {
		User u = new User();
		u.setId(this.id);
		u.setEmail(this.email);
		u.setName(this.name);
		u.setPassword(Bcrypt.getHash(this.password));
		return u;
	}

}
