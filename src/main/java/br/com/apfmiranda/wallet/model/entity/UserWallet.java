package br.com.apfmiranda.wallet.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 27 de mar de 2021
 *
 */
@Entity
@Table(name = "users_wallet")
@Data
public class UserWallet implements Serializable {

	private static final long serialVersionUID = -4248958271510011359L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JoinColumn(name = "users", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User users;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wallet", referencedColumnName = "id")
	private Wallet wallet;
	

}
