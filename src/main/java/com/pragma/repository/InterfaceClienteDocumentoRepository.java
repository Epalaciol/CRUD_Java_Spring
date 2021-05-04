package com.pragma.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pragma.model.ClienteDocumento;

@Repository
public interface InterfaceClienteDocumentoRepository extends MongoRepository<ClienteDocumento, Integer>{
	
	ClienteDocumento findByNumIdentificacion(Integer numIdentificacion);
	

}
