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
import br.com.apfmiranda.wallet.model.dto.UserWalletDTO;
import br.com.apfmiranda.wallet.model.entity.UserWallet;
import br.com.apfmiranda.wallet.service.UserWalletService;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 27 de mar de 2021
 *
 */
@RestController
@RequestMapping("userwallet")
public class UserWalletController {
	
	@Autowired
	private UserWalletService  service;
	
	@PostMapping
	public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result){
		Response<UserWalletDTO> response = new Response<>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(e ->  response.getErros().add(e.getDefaultMessage())); 
			ResponseEntity.badRequest().body(response);
		}
		
		UserWallet uw = service.save(dto.toEntity());
		response.setData(new UserWalletDTO(uw));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
