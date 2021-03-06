package br.com.apfmiranda.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apfmiranda.wallet.model.Response;
import br.com.apfmiranda.wallet.model.dto.WalletDTO;
import br.com.apfmiranda.wallet.model.entity.Wallet;
import br.com.apfmiranda.wallet.service.WalletService;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 26 de mar de 2021
 *
 */
@RestController
@RequestMapping("wallet")
public class WalletController {
	
	@Autowired
	private WalletService service;
	
	@PostMapping
	public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO dto, BindingResult result){
	
		Response<WalletDTO> response = new Response<WalletDTO>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErros().add(r.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Wallet w = service.save(dto.toEntity());
		
		response.setData(new WalletDTO(w));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
