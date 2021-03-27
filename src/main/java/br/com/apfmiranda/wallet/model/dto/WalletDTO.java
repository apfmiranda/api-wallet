package br.com.apfmiranda.wallet.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.apfmiranda.wallet.model.entity.Wallet;
import lombok.Data;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 26 de mar de 2021
 *
 */
@Data
public class WalletDTO {
	
	private Long id;
	@Length(min = 3)
	@NotNull
	private String name;
	@NotNull
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
