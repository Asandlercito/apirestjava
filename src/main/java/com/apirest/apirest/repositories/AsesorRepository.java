package com.apirest.apirest.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apirest.apirest.models.Asesor;


@Repository
public interface AsesorRepository extends CrudRepository<Asesor, Long> {

}
