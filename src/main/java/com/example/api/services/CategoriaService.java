package com.example.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Categoria;
import com.example.api.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Optional<Categoria> buscarId(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		obj.orElseThrow(() -> new NoSuchElementException());
		return obj;
	}

	public List<Categoria> buscarTodos() {
		List<Categoria> list = repository.findAll();
		if (list.isEmpty()) throw new NoSuchElementException();
		return list;
	}

	public List<Categoria> buscarNomes(String name) {
		List<Categoria> list = repository.findByNames(name);
		if (list.isEmpty()) throw new NoSuchElementException();
		return list;
	}
}
