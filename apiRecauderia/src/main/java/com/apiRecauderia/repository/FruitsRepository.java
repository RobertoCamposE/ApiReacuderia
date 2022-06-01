package com.apiRecauderia.repository;




import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.apiRecauderia.entities.Fruits;

@Repository
public interface FruitsRepository extends CrudRepository<Fruits, Integer>{
	@Transactional(readOnly = true)
	Optional<Fruits> findByClave(String clave);
	



	

	
	
	
}
