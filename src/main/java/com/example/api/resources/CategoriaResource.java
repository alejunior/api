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

import com.example.api.domain.Categoria;
import com.example.api.services.CategoriaService;

// @Validated
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

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
	public ResponseEntity<?> salvar(@RequestBody @Valid Categoria categoria){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoria.getId()).toUri();
	return ResponseEntity.created(uri).body(service.salvar(categoria));
	}

}
