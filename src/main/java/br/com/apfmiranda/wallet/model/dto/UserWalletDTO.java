package br.com.apfmiranda.wallet.model.dto;

import javax.validation.constraints.NotNull;

import br.com.apfmiranda.wallet.model.entity.UserWallet;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 27 de mar de 2021
 *
 */
@Data
@NoArgsConstructor
public class UserWalletDTO {
	
	private Long id;
	@NotNull(message = "Informe o Id do usuario")
	private Long users;
	@NotNull(message = "Informe o Id da carteira")
	private Long wallet;
	
	public UserWalletDTO (UserWallet u) {
		this.id = u.getId();
		this.users = u.getUsers().getId();
		this.wallet = u.getWallet().getId();
	}
	
	public UserWallet toEntity() {
		UserWallet u = new UserWallet();
		u.setId(id);
		return u;		
	}

}
