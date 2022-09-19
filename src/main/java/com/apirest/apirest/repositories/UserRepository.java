package com.apirest.apirest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.apirest.apirest.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
