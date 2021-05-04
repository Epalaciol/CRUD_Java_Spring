package com.pragma.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.model.ClienteDTO;
import com.prueba.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

	@Autowired
	private ClienteService servicio;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getClientes() {

		List<ClienteDTO> listaSalida = servicio.getClientesService();
		if (listaSalida.size() > 0) {

			return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.OK);
		} else {

			return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getClienteId(@PathVariable Integer id) {

		ClienteDTO clienteSalida = servicio.getClienteIdService(id);

		if (clienteSalida.getNombre() != null) {

			return new ResponseEntity<ClienteDTO>(clienteSalida, HttpStatus.OK);
		} else {
			return new ResponseEntity<ClienteDTO>(clienteSalida, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{tipo}")
	public ResponseEntity<List<ClienteDTO>> getClienteTipo(@PathVariable String tipo) {

		List<ClienteDTO> listaSalida = servicio.getClienteTipoService(tipo);
		if (listaSalida.size() > 0) {

			return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/edad/{edad}")
	public ResponseEntity<List<ClienteDTO>> getClienteEdad(@PathVariable Integer edad) {

		List<ClienteDTO> listaSalida = servicio.getClienteEdadService(edad);

		return new ResponseEntity<List<ClienteDTO>>(listaSalida, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> postCliente(@RequestBody ClienteDTO cliente) {
		
		Boolean respuesta = servicio.postClienteService(cliente);
		
		if (respuesta) {
			return new ResponseEntity<String>("Se ha guardado correctamente", HttpStatus.CREATED); 
		} else {
			return new ResponseEntity<String>(" No se pudo guardar el usuario", HttpStatus.CONFLICT);
		}

	}

	@PutMapping
	public void putCliente(@RequestBody ClienteDTO cliente) {

	}

	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable Integer id) {

	}

}
