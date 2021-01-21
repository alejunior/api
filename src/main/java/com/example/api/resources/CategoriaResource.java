package com.example.api.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscarId(id));
	}

	@GetMapping(value = "/porNome")
	public ResponseEntity<?> buscarNome(@RequestParam(value = "nome") String name) {
		return ResponseEntity.ok(service.buscarNomes(name));
	}

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		List<Categoria> listCategoria = service.buscarTodos();
		List<CategoriaDTO> listCategoriaDTO = listCategoria.stream().map(cat -> new CategoriaDTO(cat)).collect(Collectors.toList());
		return ResponseEntity.ok(listCategoriaDTO);
	}
	
	@GetMapping(value = "/pagina")
	public ResponseEntity<?> buscarTodosPagina(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> pageCategoria = service.buscarTodosPagina(page, linesPage, orderBy, direction);
		Page<CategoriaDTO> pageCategoriaDTO = pageCategoria.map(cat -> new CategoriaDTO(cat));
		return ResponseEntity.ok(pageCategoriaDTO);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@Valid @RequestBody CategoriaDTO categoriaDto){
		categoriaDto = service.salvar(categoriaDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoriaDto.getId()).toUri();
	return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> alterar(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDto){
		categoriaDto.setId(id);
		service.alterar(categoriaDto);
	return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> apagar(@PathVariable Integer id){
		service.apagar(id);
	return ResponseEntity.noContent().build();
	}

}
