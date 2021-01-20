package com.example.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.ok(service.buscarTodos());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.buscarId(id));
	}

//	@PostMapping
//	public ResponseEntity<?> salvar(@RequestBody @Valid Pedido pedido){
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(pedido.getId()).toUri();
//	return ResponseEntity.created(uri).body(service.salvar(pedido));
//	}

}
