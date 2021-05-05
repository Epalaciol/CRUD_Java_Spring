package com.pragma.model;

public class ClienteDTO {
	
	private int numIdentificacion;
	
	private String tipoIdentificacion;
	
	private String nombre;
	
	private String apellido;
	
	private String ciudadNacimiento;
	
	private int edad;
	
	private String foto;

	public int getNumIdentificacion() {
		return numIdentificacion;
	}

	public void setNumIdentificacion(int numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
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
	
	public ClienteDTO() {};
	
	public ClienteDTO(Integer id, String tipoIdentificacion, String nombre, String apellido, String ciudadNacimiento, Integer edad, String foto){
		
		this.numIdentificacion = id;
		this.tipoIdentificacion = tipoIdentificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.ciudadNacimiento = ciudadNacimiento;
		this.edad = edad;
		this.foto = foto;
		
	}
	

}
