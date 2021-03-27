package br.com.apfmiranda.wallet.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.apfmiranda.wallet.model.entity.Wallet;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 26 de mar de 2021
 *
 */
@Data
@NoArgsConstructor
public class WalletDTO {
	
	private Long id;
	@Length(min = 3, message = "O nome deve conter no minimo 3 caracteres")
	@NotNull(message = "Nome não pode ser nulo")
	private String name;
	@NotNull(message = "Insira um valor para carteira")
	private BigDecimal value;
	
	public WalletDTO(Wallet wallet) {
		this.id = wallet.getId();
		this.name = wallet.getName();
		this.value = wallet.getValue();
	}
	
	public Wallet toEntity() {
		Wallet wallet = new Wallet();
		wallet.setId(id);
		wallet.setName(name);
		wallet.setValue(value);
		return wallet;
	}

}
