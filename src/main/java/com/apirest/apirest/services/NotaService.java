package com.apirest.apirest.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apirest.apirest.repositories.NotaRepository;
import com.apirest.apirest.models.Nota;

@Service
public class NotaService implements NotaInterface {
	
	@Autowired
	private NotaRepository notaRepository;

	@Override
	public Map<String, Object> listNotas() {
		
		Map<String,Object> response =  new HashMap<>();
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		
		
		try {
			
			Iterable<Nota> notas = notaRepository.findAll();
			
			for (Nota notaa : notas) {
				
				Map<String, Object> map = new HashMap<>();
				
				String user = notaa.getUser().getApellido().toString()+" "+notaa.getUser().getNombre().toString();
				String userId = notaa.getUser().getId().toString();
												
				map.put("mensaje",notaa.getMensaje());
				map.put("codigo",notaa.getCodigo());
				map.put("full_name",user);
				map.put("usuario_id",userId);
			
				lista.add(map);
				
			}
																			
			response.put("success",true);
			response.put("data",lista);
		
		} catch (Exception e) {
			// TODO: handle exception
			response.put("success",false);
			response.put("msg",e.getMessage().toString());
		}
							
		return response;
	}
	
	@Override
	public Map<String, Object> getNotasByUser(Long userId) {
						
		Map<String,Object> response =  new HashMap<>();
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		
		
		try {
			
			List<Nota> notas = notaRepository.getNotasByUser(userId);
			
			//List<TablaMaestra> estados = ordenRepository.getEstados();	
			
			for (Nota notaa : notas) {
				
				Map<String, Object> map = new HashMap<>();
				
				String user = notaa.getUser().getApellido().toString()+" "+notaa.getUser().getNombre().toString();
				String id = notaa.getUser().getId().toString();
												
				map.put("mensaje",notaa.getMensaje());
				map.put("codigo",notaa.getCodigo());
				map.put("full_name",user);
				map.put("usuario_id",id);
			
				lista.add(map);
				
			}
																			
			response.put("success",true);
			response.put("data",lista);
		
		} catch (Exception e) {
			// TODO: handle exception
			response.put("success",false);
			response.put("msg",e.getMessage().toString());
		}
							
		return response;
	}

}
