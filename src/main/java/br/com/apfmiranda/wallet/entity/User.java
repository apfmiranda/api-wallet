package br.com.apfmiranda.wallet.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 25 de mar de 2021
 *
 */
@Entity
@Data
public class User implements Serializable {

	private static final long serialVersionUID = -1441069893796581989L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;

}
