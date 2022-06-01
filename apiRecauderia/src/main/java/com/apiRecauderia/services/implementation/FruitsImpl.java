package com.apiRecauderia.services.implementation;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apiRecauderia.dto.FruitRequest;
import com.apiRecauderia.dto.FruitsDTO;
import com.apiRecauderia.entities.Fruits;
import com.apiRecauderia.repository.FruitsRepository;
import com.apiRecauderia.services.interfaces.IFruitsService;
import com.apiRecauderia.utilities.MHelpers;

@Component
public class FruitsImpl implements IFruitsService {
	@Autowired
	private FruitsRepository fruitsRepository;

	@Override
	public List<FruitsDTO> findAll() {
		// TODO Auto-generated method stub
		List<FruitsDTO> dto = new ArrayList<>();
		Iterable<Fruits> fruits = this.fruitsRepository.findAll();
		for (Fruits fruit : fruits) {

			FruitsDTO fruitsDTO = MHelpers.modelMapper().map(fruit, FruitsDTO.class);
			dto.add(fruitsDTO);
		}
		return dto;
	}

	@Override
	public FruitsDTO findByClave(String Clave) {
		Optional<Fruits> fruits = this.fruitsRepository.findByClave(Clave);
		FruitsDTO res = null;
		if (fruits.isPresent()) {
			res = MHelpers.modelMapper().map(fruits.get(), FruitsDTO.class);
		}
		return res;
	}

	@Override
	public void save(FruitRequest fruits) {
		Fruits fruit = MHelpers.modelMapper().map(fruits, Fruits.class);
		this.fruitsRepository.save(fruit);

	}

	public void update(FruitRequest request, String clave) {
		// TODO Auto-generated method stub
		Optional<Fruits> fruits = this.fruitsRepository.findByClave(clave);
		Fruits fruit = fruits.get();
		fruit.setId(request.getId());
		fruit.setClave(request.getClave());
		fruit.setNombre(request.getNombre());
		fruit.setPrecio(request.getPrecio());
		fruit.setEstatus(request.getEstatus());
		this.fruitsRepository.save(fruit);

	}

	@Override
	public void saveAll(List<FruitRequest> fruits) {
		// TODO Auto-generated method stub

		List<Fruits> save = new ArrayList<>();
		for (FruitRequest fruit : fruits) {

			Fruits res = MHelpers.modelMapper().map(fruit, Fruits.class);
			save.add(res);
		}

		this.fruitsRepository.saveAll(save);

	}

}
