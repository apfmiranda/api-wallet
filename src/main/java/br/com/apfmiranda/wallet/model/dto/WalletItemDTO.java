package br.com.apfmiranda.wallet.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.apfmiranda.wallet.model.entity.Wallet;
import br.com.apfmiranda.wallet.model.entity.WalletItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 28 de mar de 2021
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletItemDTO {
	
	private Long id;
	@NotNull(message = "Insira o Id da carteira")
	private Long wallet;
	@NotNull(message = "Informe uma data")
	private Date date;
	@NotNull(message = "Informe um tipo")
	private String type;
	@NotNull(message = "Informe uma descrição")
	@Length(min = 5, message = "A decrição deve conter no minimo 5 caracteres")
	private String description;
	private BigDecimal value;
	
	
	public WalletItemDTO(WalletItem w) {
		this.id = w.getId();
		this.wallet = w.getWallet().getId();
		this.date = w.getDate();
		this.type = w.getType();
		this.value = w.getValue();
				
	} 
	
	
	public WalletItem toEntity() {
		Wallet w = new Wallet();
		w.setId(wallet);
		WalletItem wi = new WalletItem(id, w, date, type, description, value);
		
		return wi;
	}

}
