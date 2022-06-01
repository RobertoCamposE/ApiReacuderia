package com.apiRecauderia.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;


import com.apiRecauderia.dto.FruitRequest;
import com.apiRecauderia.dto.FruitsDTO;

@Service
public interface IFruitsService {
	List<FruitsDTO> findAll();
	FruitsDTO findByClave(String Clave);
	void save(FruitRequest fruits);
	void update(FruitRequest fruit,String clave);
	void saveAll(List<FruitRequest> tutorials);
	

	
	
}
