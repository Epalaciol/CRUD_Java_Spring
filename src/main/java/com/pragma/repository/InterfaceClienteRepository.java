package com.pragma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pragma.model.Cliente;


public interface InterfaceClienteRepository extends JpaRepository<Cliente, Integer> {
	
	List<Cliente> findByTipoIdentificacion( String tipoIdentificacion);

	List<Cliente> findByEdadGreaterThan(Integer edad);

}
