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
	private CategoriaRepository repo;

	public Optional<Categoria> buscarId(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		obj.orElseThrow(() -> new NoSuchElementException());
		return obj;
	}

	public List<Categoria> buscarTodos() {
		List<Categoria> list = repo.findAll();
		if (list.isEmpty()) {
			throw new NoSuchElementException();
		}
		return list;
	};

}
