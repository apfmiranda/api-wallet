package br.com.apfmiranda.wallet.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.apfmiranda.wallet.model.entity.Wallet;
import br.com.apfmiranda.wallet.model.entity.WalletItem;
import br.com.apfmiranda.wallet.util.enums.TypeEnum;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private Date date;
	@NotNull(message = "Informe um tipo")
	@Pattern(regexp =  "^(ENTRADA|SAIDA)$", message = "Para o tipo só são aceitos ENTRADA ou SAIDA")
	private String type;
	@NotNull(message = "Informe uma descrição")
	@Length(min = 5, message = "A decrição deve conter no minimo 5 caracteres")
	private String description;
	private BigDecimal value;
	
	
	public WalletItemDTO(WalletItem w) {
		this.id = w.getId();
		this.wallet = w.getWallet().getId();
		this.date = w.getDate();
		this.description = w.getDescription();
		this.type = w.getType().getValue();
		this.value = w.getValue();
				
	}
	
	
	public WalletItem toEntity() { 

		Wallet w = new Wallet();
		w.setId(wallet);
		
		WalletItem wi = new WalletItem(id, w, date, TypeEnum.getEnum(type), description, value);
		
		return wi;
	}
	

}
