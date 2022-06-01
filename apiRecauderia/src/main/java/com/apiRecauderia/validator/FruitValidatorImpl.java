package com.apiRecauderia.validator;


import org.springframework.stereotype.Component;

import com.apiRecauderia.dto.FruitRequest;
import com.apiRecauderia.dto.FruitsDTO;
import com.apiRecauderia.services.implementation.FruitsImpl;

import com.apiRecauderia.utilities.exceptions.ApiUnprocessableEntity;

@Component
public class FruitValidatorImpl implements FruitValidator {

	@Override
	public void validator(FruitRequest request) throws ApiUnprocessableEntity {
		// TODO Auto-generated method stub
		if (request.getNombre() == null || request.getNombre().isEmpty()) {
			this.message("El nombre es obligatorio");
		}
		if (request.getNombre().length() < 4) {
			this.message("El nombre es demasiado corto");
		}
		if (request.getClave() == null || request.getClave().isEmpty()) {
			this.message("La Clave es obligatoria");
		}
		if (request.getClave().length() < 5 || request.getClave().length() > 5) {
			this.message("La clave debe ser de 5 caracteres");
		}
		if (request.getPrecio() < 0) {
			this.message("No pueden existir precios negativos");
		}

	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}

	@Override
	public boolean exist(String Clave) throws ApiUnprocessableEntity {
		// TODO Auto-generated method stub
		return false;
	}

	
	


	}
