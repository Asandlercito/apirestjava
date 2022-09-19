package com.apirest.apirest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.apirest.apirest.repositories.UserRepository;

import com.apirest.apirest.models.User;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
	
	@Autowired
	UserRepository userRepo;
	
	@SuppressWarnings("rawtypes")	
	@RequestMapping(
		value = "/usuarios",
		method = RequestMethod.POST,
		consumes  = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> create(@RequestBody Map<String,Object> data ) {
								
		Map<String, Object> payload = new HashMap<>();	
		
		ResponseEntity response   = null;
		
		HttpHeaders headers = new HttpHeaders();
		
		User usuario = new User();
					
		usuario.setNombre(data.get("nombres").toString());
		usuario.setApellido(data.get("apellidos").toString());
		usuario.setTelefono(data.get("telefono").toString());
		usuario.setPassword(data.get("password").toString());
		usuario.setEmail(data.get("email").toString());
		
		userRepo.save(usuario);
		
		payload.put("success",true);
		payload.put("data",usuario.getId());
				
		response = new ResponseEntity<Map<String, Object>>( payload, headers, HttpStatus.OK );
											
		return response;
							
	}

}
