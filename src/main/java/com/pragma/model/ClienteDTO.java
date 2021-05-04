package com.pragma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


public class ClienteDTO {
	
	private int numIdentificiacion;
	
	private String tipoIdentificacion;
	
	private String nombre;
	
	private String apellido;
	
	private String ciudadNacimiento;
	
	private int edad;
	
	private String foto;

	public int getNumIdentificiacion() {
		return numIdentificiacion;
	}

	public void setNumIdentificiacion(int numIdentificiacion) {
		this.numIdentificiacion = numIdentificiacion;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCiudadNacimiento() {
		return ciudadNacimiento;
	}

	public void setCiudadNacimiento(String ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

}
