package com.example.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.domain.Cliente;
import com.example.api.domain.Endereco;
import com.example.api.repositories.ClienteRepository;
import com.example.api.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Transactional(readOnly = true)
	public Optional<Cliente> buscarId(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		obj.orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado."));
		return obj;
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos() {
		List<Cliente> list = repository.findAll();
		if (list.isEmpty()) throw new ObjectNotFoundException("Recurso não encontrado.");
		return list;
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarNomes(String name) {
		List<Cliente> list = repository.findByName(name);
		if (list.isEmpty()) throw new ObjectNotFoundException("Recurso não encontrado.");
		return list;
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		Cliente cli = new Cliente(null, cliente.getNome(), cliente.getEmail(), cliente.getCpfOuCnpj(), cliente.getTipo());
		for(String t : cliente.getTelefone()) {
			cli.getTelefone().add(t);
		}
		for(Endereco e : cliente.getEndereco()) {
			Endereco end = new Endereco(null, e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getBairro(), e.getCep());
			cli.getEndereco().add(end);
		}
		return repository.save(cli);
	}
}
