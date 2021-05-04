package com.prueba.service;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pragma.model.Cliente;
import com.pragma.model.ClienteDTO;
import com.pragma.model.ClienteDocumento;
import com.pragma.repository.InterfaceClienteDocumentoRepository;
import com.pragma.repository.InterfaceClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private InterfaceClienteRepository clienteRepository;

	@Autowired
	private InterfaceClienteDocumentoRepository documentoRepository;

	public List<ClienteDTO> getClientesService() {

		List<Cliente> clientesRecibidos = clienteRepository.findAll();

		List<ClienteDTO> clientesSalida;

		clientesSalida = armarClientesDTO(clientesRecibidos);

		return clientesSalida;
	}

	public ClienteDTO getClienteIdService(Integer id) {

		Optional<Cliente> clienteBusqueda = clienteRepository.findById(id);
		
		if (clienteBusqueda.isEmpty()) {

			return new ClienteDTO();

		} else {
			ClienteDocumento clienteDocumentoBusqueda = documentoRepository.findByNumIdentificacion(id);
			ClienteDTO clienteSalida = armarClienteDTO(clienteBusqueda.get(), clienteDocumentoBusqueda);

			return clienteSalida;
		}

	}
	
	public List<ClienteDTO> getClienteTipoService(String tipo){
		
		List<ClienteDTO> listaSalida = new ArrayList<>();
		List<Cliente> clienteBusqueda= clienteRepository.findByTipoIdentificacion(tipo);
		
		
		if (clienteBusqueda.isEmpty()) {

			return listaSalida;
		}else {
			return armarClientesDTO(clienteBusqueda);
		}
		
	}
	
	public  List<ClienteDTO> getClienteEdadService(Integer edad){

		List<ClienteDTO> listaSalida = new ArrayList<>();
		
		List<Cliente> clienteBusqueda= clienteRepository.findByEdadGreaterThan(edad);
		
		if (clienteBusqueda.isEmpty()) {

			return listaSalida;
		}else {
			return armarClientesDTO(clienteBusqueda);
		}
		
		
	}

	public List<ClienteDTO> armarClientesDTO(List<Cliente> cliente) {
		List<ClienteDTO> salida = new ArrayList<>();

		for (int i = 0; i < cliente.size(); i++) {

			Cliente clienteTemporal = cliente.get(i);

			ClienteDocumento clienteDocTemporal = documentoRepository
					.findByNumIdentificacion(clienteTemporal.getNumIdentificiacion());

			ClienteDTO clienteAnexar = armarClienteDTO(clienteTemporal, clienteDocTemporal);

			salida.add(clienteAnexar);

		}
		return salida;

	}
	public Boolean postClienteService(ClienteDTO cliente) {
		
		Cliente clienteGuardar = new Cliente();
		ClienteDocumento clienteDocGuardar = new ClienteDocumento();
		
		clienteGuardar.setNombre(cliente.getNombre());
		clienteGuardar.setApellido(cliente.getApellido());
		clienteGuardar.setNumIdentificiacion(cliente.getNumIdentificiacion()); 
		clienteGuardar.setTipoIdentificacion(cliente.getTipoIdentificacion());
		clienteGuardar.setCiudadNacimiento(cliente.getCiudadNacimiento());
		clienteGuardar.setEdad(cliente.getEdad());
		
		clienteDocGuardar.setNumIdentificiacion(cliente.getNumIdentificiacion()); 
		clienteDocGuardar.setFoto(cliente.getFoto());
		
		try {
			clienteRepository.save(clienteGuardar);
			documentoRepository.save(clienteDocGuardar);
			return true;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}

		
		
		
	}
	
	public ClienteDTO armarClienteDTO(Cliente cliente, ClienteDocumento clienteDocumento) {
		ClienteDTO clienteSalida = new ClienteDTO();

		clienteSalida.setNombre(cliente.getNombre());
		clienteSalida.setApellido(cliente.getApellido());
		clienteSalida.setNumIdentificiacion(cliente.getNumIdentificiacion());
		clienteSalida.setTipoIdentificacion(cliente.getTipoIdentificacion());
		clienteSalida.setCiudadNacimiento(cliente.getCiudadNacimiento());
		clienteSalida.setEdad(cliente.getEdad());
		clienteSalida.setFoto(clienteDocumento.getFoto());

		return clienteSalida;
	}

}
