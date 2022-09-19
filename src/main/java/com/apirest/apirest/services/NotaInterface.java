package com.apirest.apirest.services;

import java.util.Map;


public interface NotaInterface{
	
	
	Map<String ,Object> listNotas();

	Map<String, Object> getNotasByUser(Long userId);

}
