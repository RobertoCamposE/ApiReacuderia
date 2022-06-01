package com.apiRecauderia.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;



public class FruitRequest implements Serializable {
	@JsonProperty("id")
	private int id;
	private boolean estatus =true;
	@JsonProperty("clave")
	private String clave;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("precio")
	private double precio;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public FruitRequest() {
		super();
	}
	public FruitRequest(int id,String clave,String nombre,double precio) {
		this.id=id;
		this.clave=clave;
		this.nombre=nombre;
		this.precio=precio;
		this.estatus=true;
	}
}
