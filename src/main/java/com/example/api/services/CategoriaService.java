package com.example.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.domain.Categoria;
import com.example.api.domain.Produto;
import com.example.api.repositories.CategoriaRepository;
import com.example.api.repositories.ProdutoRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional(readOnly = true)
	public Optional<Categoria> buscarId(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		obj.orElseThrow(() -> new NoSuchElementException());
		return obj;
	}

	@Transactional(readOnly = true)
	public List<Categoria> buscarTodos() {
		List<Categoria> list = repository.findAll();
		if (list.isEmpty()) throw new NoSuchElementException();
		return list;
	}

	@Transactional(readOnly = true)
	public List<Categoria> buscarNomes(String name) {
		List<Categoria> list = repository.findByNames(name);
		if (list.isEmpty()) throw new NoSuchElementException();
		return list;
	}

	@Transactional
	public Categoria salvar(Categoria categoria) {
		Categoria cat = new Categoria(null, categoria.getNome());
		for(Produto p : categoria.getProdutos()) {
			Produto product = produtoRepository.getOne(p.getId());
			cat.getProdutos().add(product);
		}
		return repository.save(cat);
	}
}
