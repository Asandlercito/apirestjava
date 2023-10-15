package com.apirest.apirest.controllers;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apirest.apirest.repositories.NotaRepository;
import com.apirest.apirest.repositories.UserRepository;
import com.apirest.apirest.response.ResponseHandler;
import com.apirest.apirest.services.NotaInterface;
import com.apirest.apirest.utils.JWTUtil;
import com.apirest.apirest.models.Nota;
import com.apirest.apirest.models.User;
import com.apirest.apirest.response.ResponseHandler;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {
		RequestMethod.GET,
		RequestMethod.POST,
		RequestMethod.DELETE,
		RequestMethod.PATCH
		})

public class NotaController {
	
    @Autowired
    private NotaRepository notaRepository;
    
    @Autowired
    private JWTUtil jwtUtil;
    
    @Autowired
    private UserRepository userRepo;
    
    
    @Autowired
    private NotaInterface notaInterfaceService;
    
    
	@SuppressWarnings("rawtypes")	
	@GetMapping(
		value = "/notas",
		consumes  = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)	
	public ResponseEntity<?> list() {
		
		ResponseEntity response = null;		
		HttpHeaders    headers  = new HttpHeaders();
							
		response = new ResponseEntity<Map<String, Object>>(notaInterfaceService.listNotas(), headers, HttpStatus.OK);
											
		return response;
	}
    
	@SuppressWarnings("rawtypes")	
	@PostMapping(
		value = "/notas",
		consumes  = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)	
	public ResponseEntity<?> create(@RequestHeader(value="Authorization") String token,@RequestBody Map<String, Object> data ) {
		
		/* POR TEMAS DE PRACTICIDAD NO CREE UN METODO EN EL SERVICIO PARA ESTO , EN EL ULTIMO METODO IMPLEMENTO UN SERVICIO*/
		
		ResponseEntity response   = null;		
		HttpHeaders    headers    = new HttpHeaders();
		
		Map<String, Object> payload = new HashMap<>();
		
		if (!validarToken(token)) {
			
			payload.put("success",false);
			payload.put("msg","toke no validado");
			
			response = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.OK);
			
			return response;
		}
		
		Long userId = Long.parseLong(data.get("user_id").toString());
		
		User userFound = userRepo.findById((long)userId).orElse(null);
		
		if(userFound ==  null) {
			
			payload.put("success",false);
			payload.put("msg","user not found");
			
			response = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.BAD_REQUEST);
			
			return response; 
		}
					
		Nota nota = new Nota();
		
		nota.setMensaje(data.get("mensaje").toString());
		nota.setCodigo(data.get("codigo").toString());
		nota.setUser(userFound);
		
		notaRepository.save(nota);
		
		
		payload.put("success",true);
		payload.put("data",nota.getId());
				
		response = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.CREATED);
											
		return response;
	}
	
	@SuppressWarnings("rawtypes")	
	@PatchMapping(
		value = "/notas",
		consumes  = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)	
	public ResponseEntity<?> update(@RequestBody Map<String,Object> data) {
		

		Map<String, Object> payload = new HashMap<>();	
		
		ResponseEntity response = null;		
		HttpHeaders    headers  = new HttpHeaders();
		Nota nota = null;
		
		Long notaId = Long.parseLong(data.get("id").toString());
		
		nota = notaRepository.findById(notaId).orElse(null);
							
		if(nota == null) {
			
			payload.put("success",false);
			payload.put("msg","nota not found");
			
			response = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.OK);
			
			return response;
		}
				
		nota.setCodigo(data.get("codigo").toString());
		nota.setMensaje(data.get("mensaje").toString());
		
		notaRepository.save(nota);
		
	
		payload.put("success",true);
		payload.put("data",nota.getId());
									
		response = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.OK);
											
		return response;
	}
	
	@SuppressWarnings("rawtypes")	
	@DeleteMapping(
		value = "/notas/{id}",
		consumes  = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)	
	public ResponseEntity<?> delete(@RequestHeader(value="Authorization") String token,@PathVariable("id") String id) {
		
		Map<String, Object> payload = new HashMap<>();
		
		ResponseEntity response = null;		
		HttpHeaders    headers  = new HttpHeaders();
		Nota nota = null;
		
		Long notaId = Long.parseLong(id.toString());
		
		nota = notaRepository.findById(notaId).orElse(null);
							
		if(nota == null) {
			
			payload.put("success",false);
			payload.put("msg","no se pudo encontra la nota");
			
			response = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.OK);
			
			return response;
		}
				
		notaRepository.delete(nota);
				
		payload.put("success",true);
		payload.put("msg","Eliminado Satisfactoriamente");
									
		response = new ResponseEntity<Map<String, Object>>(payload, headers, HttpStatus.OK);
											
		return response;
	}
	
	@GetMapping(
		value = "/notas/{id}",
		consumes  = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)	
	public ResponseEntity<?> read(@PathVariable("id") String id) {
				
	    Nota nota = notaInterfaceService.giveMe(Long.parseLong(id));

	    if (nota != null) {	    	
	        return ResponseHandler.responseBuilder("found", HttpStatus.OK, nota);	        
	    } else {
	    	return ResponseHandler.responseBuilder("not found", HttpStatus.NOT_FOUND, nota);
	    }
											
	}
	
	@SuppressWarnings("rawtypes")	
	@GetMapping(
		value = "/notas/user/{user_id}",
		consumes  = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)	
	public ResponseEntity<?> getNotasByUser(@PathVariable("user_id") String userId) {
		
		ResponseEntity response = null;		
		HttpHeaders    headers  = new HttpHeaders();
							
		response = new ResponseEntity<Map<String, Object>>(notaInterfaceService.getNotasByUser(Long.parseLong(userId)), headers, HttpStatus.OK);
											
		return response;
	}
	
    @SuppressWarnings("unused")
	private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }
	
}
