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

import br.com.apfmiranda.wallet.dto.UserDTO;
import br.com.apfmiranda.wallet.entity.User;
import br.com.apfmiranda.wallet.response.Response;
import br.com.apfmiranda.wallet.service.UserService;

/**
 * @author Alexandre Pires Ferrerira de Miranda <alexandre.pires@nagem.com.br>
 *
 * 25 de mar de 2021
 *
 */
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping
	public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO dto, BindingResult result) {
		
		Response<UserDTO> response = new Response<UserDTO>(); 
		
		User user = service.save(dto.toEntity());
		
		response.setData(new UserDTO(user));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	

}
