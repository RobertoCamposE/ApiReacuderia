package com.apiRecauderia.validator;

import org.springframework.stereotype.Service;

import com.apiRecauderia.dto.FruitRequest;
import com.apiRecauderia.utilities.exceptions.ApiUnprocessableEntity;
//Interface para validacion de datos recibidos para la creacion de usuarios
@Service
public interface FruitValidator {
	void validator(FruitRequest request)throws ApiUnprocessableEntity;
	boolean exist(String Clave)throws ApiUnprocessableEntity;
}
