package br.com.apfmiranda.wallet.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Entity
@Table(name = "wallet_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletItem implements Serializable {
	private static final long serialVersionUID = -4467668182910743957L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JoinColumn(name = "wallet", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Wallet wallet;
	@NotNull
	private Date date;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeEnum type;
	@NotNull	
	private String description;
	@NotNull	
	private BigDecimal value;

}
