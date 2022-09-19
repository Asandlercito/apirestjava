package com.apirest.apirest.controllers;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.apirest.apirest.repositories.AsesorRepository;
import com.apirest.apirest.models.Asesor;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE})

public class AsesorController {
	
    @Autowired
    private AsesorRepository asesorRepo;
    
    
	@SuppressWarnings("rawtypes")	
	@RequestMapping(
		value = "/asesores",
		method = RequestMethod.POST,
		consumes  = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)	
	public ResponseEntity<?> create(@RequestBody Map<String, Object> data ) {
		
		Map<String, Object> payload = new HashMap<>();	
		
		ResponseEntity response   = null;		
		HttpHeaders headers = new HttpHeaders();
		
		Asesor asesor = new Asesor();
		
		asesor.setApellidos("soria fiestas".toString());
		asesor.setNombres("giancarlo".toString());
		asesor.setCodigo("0001".toString());
		
		asesorRepo.save(asesor);
		

		payload.put("success",true);
		payload.put("data",asesor.getId());
				
		response = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.OK);
											
		return response;
	}
	
	@SuppressWarnings("rawtypes")	
	@RequestMapping(
		value = "/asesores/{id}",
		method = RequestMethod.GET,
		consumes  = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)	
	public ResponseEntity<?> getAsesor(@PathVariable("id") String idAsesor) {
		
		System.out.printf("Asesor id "+idAsesor);
		Map<String, Object> payload = new HashMap<>();	
		
		ResponseEntity response = null;		
		HttpHeaders    headers  = new HttpHeaders();
		
		Object asesor = asesorRepo.findById(Long.parseLong(idAsesor));
		
		if (!asesor.equals(null)) {
			payload.put("success",true);
			payload.put("data",asesor);
		} else {
			payload.put("success",false);
			payload.put("msg","el melgar perdio");
		}
						
		response = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.OK);
											
		return response;
	}


}
