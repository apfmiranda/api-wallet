package br.com.apfmiranda.wallet.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 25 de mar de 2021
 *
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
public class Response<T>{
	private T data;
	private List<String> erros;
}
