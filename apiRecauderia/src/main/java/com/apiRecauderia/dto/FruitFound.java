package com.apiRecauderia.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class FruitFound implements Serializable{
	@JsonProperty("clave")
	private String clave;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public FruitFound() {
		super();
	}
	
	
}
