package com.apirest.apirest.services;

import java.util.Map;
import java.util.Optional;

import com.apirest.apirest.models.Nota;

public interface NotaInterface{
	
	
	public Map<String ,Object> listNotas();

	public Map<String, Object> getNotasByUser(Long userId);
	
	public Map<String, Object> getNotaById(Long conchatumare);

	public Nota giveMe(Long notaId);

}
