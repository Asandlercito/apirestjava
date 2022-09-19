package com.apirest.apirest.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.apirest.apirest.models.Nota;

@Repository
public interface NotaRepository extends CrudRepository<Nota,Long> {
	
	
	@Query(value="SELECT * FROM notas  WHERE user_id =:userId",nativeQuery = true)
	public List<Nota> getNotasByUser(Long userId);

}
