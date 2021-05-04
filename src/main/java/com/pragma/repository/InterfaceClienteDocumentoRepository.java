package com.pragma.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pragma.model.ClienteDocumento;


public interface InterfaceClienteDocumentoRepository extends MongoRepository<ClienteDocumento, Integer>{
	
	ClienteDocumento findBynumIdentificacion(Integer numIdentificacion);
	
	void deleteByNumIdentificacion(Integer numIdentificacionInteger);
	

}
