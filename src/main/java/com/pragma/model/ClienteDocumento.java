package com.pragma.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ClienteDocumento {
	
	@Id
	private int numIdentificacion;
	
	private String foto;
	
	public int getNumIdentificiacion() {
		return numIdentificacion;
	}

	public void setNumIdentificiacion(int numIdentificacion) {
		this.numIdentificacion = numIdentificacion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


}
