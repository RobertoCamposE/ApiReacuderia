package com.apiRecauderia.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/*Excepcion personalizada 404 no encontrada*/
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFound extends Exception{
	public ApiNotFound(String message){
		super(message);
	}
}
