package com.example.api.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.api.domain.Cliente;
import com.example.api.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.ok(service.buscarTodos());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscarId(id));
	}

	@GetMapping(value = "/porNome")
	public ResponseEntity<?> buscarNome(String name) {
		return ResponseEntity.ok(service.buscarNomes(name));
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid Cliente cliente){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
	return ResponseEntity.created(uri).body(service.salvar(cliente));
	}

}
