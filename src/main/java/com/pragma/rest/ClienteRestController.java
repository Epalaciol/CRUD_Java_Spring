package com.pragma.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.model.Cliente;
import com.pragma.model.ClienteDTO;
import com.pragma.repository.InterfaceClienteRepository;
import com.pragma.service.ClienteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

	@Autowired
	private ClienteService servicio;
	
	@Autowired
	private InterfaceClienteRepository clienteRepository;

	@ApiOperation(value="Obtener todos los clientes" )
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=ClienteDTO.class),
	@ApiResponse(code=204, message="No Content", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class)
	})
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getClientes() {

		List<ClienteDTO> listaSalida = servicio.getClientesService();
		if (listaSalida.size() > 0) {

			return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.OK);
		} else {

			return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.NO_CONTENT);
		}

	}
	
	@ApiOperation(value="Obtener un cliente por Id" )
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=ClienteDTO.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=204, message="No Content", response=String.class)
	})
	@GetMapping("/id={id}")
	public ResponseEntity<ClienteDTO> getClienteId(@PathVariable Integer id) {

		ClienteDTO clienteSalida = servicio.getClienteIdService(id);

		if (clienteSalida.getNombre() != null) {

			return new ResponseEntity<ClienteDTO>(clienteSalida, HttpStatus.OK);
		} else {
			return new ResponseEntity<ClienteDTO>(clienteSalida, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value="Obtener clientes por tipo de Identificación" )
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=ClienteDTO.class),
	@ApiResponse(code=204, message="No Content", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class)
	})
	@GetMapping("/tipo={tipo}")
	public ResponseEntity<List<ClienteDTO>> getClienteTipo(@PathVariable String tipo) {

		List<ClienteDTO> listaSalida = servicio.getClienteTipoService(tipo);
		if (listaSalida.size() > 0) {

			return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.NO_CONTENT);
		}

	}
	
	@ApiOperation(value="Obtener clientes mayores a la edad buscada" )
	@ApiResponses(value={
	@ApiResponse(code=200, message="OK", response=ClienteDTO.class),
	@ApiResponse(code=204, message="No Content", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class)
	})
	@GetMapping("/edad={edad}")
	public ResponseEntity<List<ClienteDTO>> getClienteEdad(@PathVariable Integer edad) {

		List<ClienteDTO> listaSalida = servicio.getClienteEdadService(edad);

		return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.OK);
	}

	@ApiOperation(value="Insertar un nuevo cliente en el sistema" )
	@ApiResponses(value={
	@ApiResponse(code=201, message="Se ha guardado correctamente", response=String.class),
	@ApiResponse(code=204, message="No Content", response=String.class),
	@ApiResponse(code=400, message="Peticion mal hecha", response=String.class),
	@ApiResponse(code=404, message="Not Found", response=String.class),
	@ApiResponse(code=409, message="Usuario ya existe en el sistema", response=String.class)
	})
	@PostMapping
	public ResponseEntity<String> postCliente(@Validated(value = ClienteDTO.class) @RequestBody ClienteDTO cliente) {
		
		Optional<Cliente> busqueda = clienteRepository.findById(cliente.getNumIdentificacion());
		
		if(!busqueda.isEmpty()) {
			return new ResponseEntity<String>("Usuario ya existe en el sistema", HttpStatus.CONFLICT);
		}
		
		Boolean respuesta = servicio.postClienteService(cliente);
		
		if (respuesta) {
			return new ResponseEntity<String>("Se ha guardado correctamente", HttpStatus.CREATED); 
		} else {
			return new ResponseEntity<String>(" No se pudo guardar el usuario", HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@ApiOperation(value="Modificar un cliente  guardado en el sistema" )
	@ApiResponses(value={
	@ApiResponse(code=202, message="Se ha modificado correctamente", response=String.class),
	@ApiResponse(code=400, message="Peticion mal hecha", response=String.class),
	@ApiResponse(code=404, message="El cliente no esta registrado en el sistema", response=String.class)
	})
	@PutMapping("/{id}")
	public ResponseEntity<String> putCliente(@RequestBody ClienteDTO cliente, @PathVariable Integer id) {
		Optional<Cliente> busqueda = clienteRepository.findById(id);
		
		if(busqueda.isEmpty()) {
			return new ResponseEntity<String>("El cliente no esta registrado en el sistema", HttpStatus.NOT_FOUND);
		}
		Boolean respuesta = servicio.putClienteService(id, cliente);
		
		if (respuesta) {
			return new ResponseEntity<String>("Se ha modificado correctamente", HttpStatus.ACCEPTED); 
		} else {
			return new ResponseEntity<String>(" No se pudo guardar el usuario (Verifique los datos he intentelo de nuevo)", HttpStatus.BAD_REQUEST);
		}
		

	}
	
	@ApiOperation(value="Borrar un cliente" )
	@ApiResponses(value={
	@ApiResponse(code=200, message="Se ha eliminado correctamente", response=String.class),
	@ApiResponse(code=400, message="No se pudo eliminar el usuario", response=String.class),
	@ApiResponse(code=404, message="El cliente no esta registrado en el sistema", response=String.class)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable Integer id) {
		Optional<Cliente> busqueda = clienteRepository.findById(id);
		
		if(busqueda.isEmpty()) {
			return new ResponseEntity<String>("El cliente no esta registrado en el sistema", HttpStatus.NOT_FOUND);
		}	
		Boolean respuesta = servicio.deleteClienteService(id);
		
		if (respuesta) {
			return new ResponseEntity<String>("Se ha eliminado correctamente", HttpStatus.OK); 
		} else {
			return new ResponseEntity<String>(" No se pudo eliminar el usuario", HttpStatus.BAD_REQUEST);
		}

	}

}
