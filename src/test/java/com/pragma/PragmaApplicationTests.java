package com.pragma;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.base.Optional;
import com.pragma.model.Cliente;
import com.pragma.model.ClienteDTO;
import com.pragma.model.ClienteDocumento;
import com.pragma.repository.InterfaceClienteDocumentoRepository;
import com.pragma.repository.InterfaceClienteRepository;
import com.pragma.service.ClienteService;


@SpringBootTest
class PragmaApplicationTests {
	
	@InjectMocks
	ClienteService servicio;
	
	@Mock
	InterfaceClienteRepository clienteRepository;
	
	@Mock
	InterfaceClienteDocumentoRepository clienteDocRepository;
	
	@Mock
	Cliente cliente;
	
	@Mock
	ClienteDocumento clienteDoc;
	
	@Mock
	ClienteDTO clienteDTO;
	
	@Mock
	List<ClienteDTO> clienteDTOs; 
	
	@Mock
	List<Cliente> clientes;
	
	@Mock
	List<ClienteDocumento> clientesDocumento;
	
	@Before
	public void iniciar() {
		ClienteDTO clienteDTO = new ClienteDTO(1, "cc","Pureba", "Uno", "MDE", 18, "FOTO 1");
		List<ClienteDTO> clienteDTOs = new ArrayList<ClienteDTO>();
		clienteDTOs.add(clienteDTO);
		
		Cliente cliente = new Cliente(1, "cc","Pureba", "Uno", "MDE", 18);
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente);
		
		ClienteDocumento clienteDoc = new ClienteDocumento(1, "FOTO 1");
		List<ClienteDocumento> clientesDocumento = new ArrayList<ClienteDocumento>();
		clientesDocumento.add(clienteDoc);
		
	}
	
	@Test
	public void getClientesTest() {
		
		when(clienteRepository.findAll()).thenReturn(clientes);
		when(clienteDocRepository.findAll()).thenReturn(clientesDocumento);
		
		List<ClienteDTO> resultado = servicio.getClientesService();
		
		assertNotNull(resultado);
	}
	
	
	@Test
	public void getClienteIdTest() {
		
		java.util.Optional<Cliente> clientePrueba = java.util.Optional.of(cliente);
		
		when(clienteRepository.findById(1)).thenReturn(clientePrueba);
		when(clienteDocRepository.findBynumIdentificacion(1)).thenReturn(clienteDoc);
		
		ClienteDTO resultado = servicio.getClienteIdService(1);
		
		assertNotNull(resultado);
		assertEquals(cliente.getTipoIdentificacion(), resultado.getTipoIdentificacion());
		
	}
	
	@Test
	public void getClienteTipoTest() {

		when(clienteRepository.findByTipoIdentificacion("cc")).thenReturn(clientes);
		
		List<ClienteDTO> resultado = servicio.getClienteTipoService("cc");
		System.out.println(resultado);
		
		assertNotNull(resultado);		
	}
	
	@Test
	public void getClienteEdadTest() {

		when(clienteRepository.findByEdadGreaterThan(20)).thenReturn(clientes);
		
		List<ClienteDTO> resultado = servicio.getClienteEdadService(20);
		System.out.println(resultado);
				
		assert(resultado.isEmpty());
	}
	
	@Test
	public void postClienteTest() {
		
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		when(clienteDocRepository.save(clienteDoc)).thenReturn(clienteDoc);
		
		Boolean resultado = servicio.postClienteService(clienteDTO);
		
		assertEquals(true, resultado);
		
	}
	
	@Test
	public void putClienteTest() {
		
		when(clienteRepository.save(cliente)).thenReturn(cliente);
		when(clienteDocRepository.save(clienteDoc)).thenReturn(clienteDoc);
		
		Boolean resultado = servicio.putClienteService(1, clienteDTO);
		
		assertEquals(true, resultado);
	}
	
	@Test 
	public void deleteClienteTest() {
		
		Boolean resultado = servicio.deleteClienteService(1);
		
		assertEquals(true, resultado);
	}

}
