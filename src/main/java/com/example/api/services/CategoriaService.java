package com.example.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.domain.Categoria;
import com.example.api.repositories.CategoriaRepository;
import com.example.api.services.exception.DataIntegrityException;
import com.example.api.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public Optional<Categoria> buscarId(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		obj.orElseThrow(() -> new ObjectNotFoundException("Recurso não encontrado."));
		return obj;
	}

	@Transactional(readOnly = true)
	public List<Categoria> buscarTodos() {
		List<Categoria> list = repository.findAll();
		if (list.isEmpty())
			throw new ObjectNotFoundException("Recurso não encontrado.");
		return list;
	}

	@Transactional(readOnly = true)
	public List<Categoria> buscarNomes(String name) {
		List<Categoria> list = repository.findByNames(name);
		if (list.isEmpty())
			throw new ObjectNotFoundException("Recurso não encontrado.");
		return list;
	}

	public Categoria salvar(Categoria categoria) {
		categoria.setId(null);
		try {
			categoria = repository.save(categoria);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Recurso já cadastrado.");
		}
		return categoria;
	}

	public Categoria alterar(Categoria categoria) {
		buscarId(categoria.getId());
		return repository.save(categoria);
	}

	public void apagar(Integer id) {
		buscarId(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Categoria possui produtos atrelados e portanto não pode ser excluida.");
		}
	}

}
