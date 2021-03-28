package br.com.apfmiranda.wallet.model.dto;

import javax.validation.constraints.NotNull;

import br.com.apfmiranda.wallet.model.entity.User;
import br.com.apfmiranda.wallet.model.entity.UserWallet;
import br.com.apfmiranda.wallet.model.entity.Wallet;
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
	
	/**
	 * Convert DTO para entidade
	 * 
	 * 
	 * @return
	 */
	public UserWallet toEntity() {
		UserWallet u = new UserWallet();
		
		User user = new User();
		user.setId(this.users);
		
		Wallet w = new Wallet();
		w.setId(wallet);
		
		u.setId(id);
		u.setUsers(user);
		u.setWallet(w);
		
		return u;		
	}

}
