package com.example.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.api.domain.Categoria;
import com.example.api.dto.CategoriaDTO;
import com.example.api.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		List<Categoria> listCategoria = service.buscarTodos();
		List<CategoriaDTO> listCategoriaDTO = listCategoria.stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
		return ResponseEntity.ok(listCategoriaDTO);
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
	public ResponseEntity<?> salvar(@Valid @RequestBody Categoria categoria){
		categoria = service.salvar(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoria.getId()).toUri();
	return ResponseEntity.created(uri).body(categoria);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> alterar(@Valid @PathVariable Integer id, @RequestBody Categoria categoria){
		categoria.setId(id);
		service.alterar(categoria);
	return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> apagar(@Valid @PathVariable Integer id){
		service.apagar(id);
	return ResponseEntity.noContent().build();
	}

}
