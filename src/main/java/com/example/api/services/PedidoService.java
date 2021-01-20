package com.example.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.domain.Pedido;
import com.example.api.repositories.PedidoRepository;
import com.example.api.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Transactional(readOnly = true)
	public Optional<Pedido> buscarId(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		obj.orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado."));
		return obj;
	}

	@Transactional(readOnly = true)
	public List<Pedido> buscarTodos() {
		List<Pedido> list = repository.findAll();
		if (list.isEmpty()) throw new ObjectNotFoundException("Recurso não encontrado.");
		return list;
	}

}
